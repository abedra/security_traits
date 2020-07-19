package com.aaronbedra.web;

import com.aaronbedra.web.request.RestClient;
import com.jnape.palatable.lambda.functions.Fn1;
import com.jnape.palatable.lambda.functor.builtin.State;
import com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT;
import lombok.Value;
import okhttp3.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.aaronbedra.web.request.Hostname.hostname;
import static com.jnape.palatable.lambda.functions.builtin.fn1.Constantly.constantly;
import static com.jnape.palatable.lambda.functor.builtin.State.state;
import static com.jnape.palatable.lambda.monad.transformer.builtin.ReaderT.readerT;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertThat;
import static testsupport.matchers.StateMatcher.whenEvaluated;

public class WebRequesterTest {
    private Response.Builder responseBuilder;

    @Before
    public void before() {
        Request request = new Request
                .Builder()
                .url("https://localhost")
                .build();

        responseBuilder = new Response
                .Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("");
    }

    @Test
    public void responseCode() {
        Response response = responseBuilder
                .code(200)
                .build();
        ReaderT<Request, State<Response, ?>, Response> responseReaderT = readerT(constantly(state(response)));
        TestClient testClient = new TestClient(responseReaderT, x -> emptyList());
        WebRequester<State<Response, ?>, TestCookie> webRequester = WebRequester.webRequester(hostname("localhost"), testClient);

        State<Response, Integer> state = webRequester
                .requestHttp()
                .fmap(Response::code)
                .coerce();

        assertThat(state, whenEvaluated(response, 200));
    }

    @Test
    public void header() {
        Response response = responseBuilder
                .code(200)
                .addHeader("X-Test", "test")
                .build();
        ReaderT<Request, State<Response, ?>, Response> responseReaderT = readerT(constantly(state(response)));
        TestClient testClient = new TestClient(responseReaderT, x -> emptyList());
        WebRequester<State<Response, ?>, TestCookie> webRequester = WebRequester.webRequester(hostname("localhost"), testClient);

        State<Response, String> state = webRequester
                .requestHttp()
                .fmap(Response::headers)
                .fmap(pairs -> pairs.get("X-Test"))
                .coerce();

        assertThat(state, whenEvaluated(response, "test"));
    }

    @Test
    public void body() {
        Response response = responseBuilder
                .code(200)
                .body(ResponseBody.create("body", MediaType.parse("text/plain")))
                .build();
        ReaderT<Request, State<Response, ?>, Response> responseReaderT = readerT(constantly(state(response)));
        TestClient testClient = new TestClient(responseReaderT, x -> emptyList());
        WebRequester<State<Response, ?>, TestCookie> webRequester = WebRequester.webRequester(hostname("localhost"), testClient);

        State<Response, String> state = webRequester
                .requestHttp()
                .fmap(Response::body)
                .fmap(ResponseBody::string)
                .coerce();

        assertThat(state, whenEvaluated(response, "body"));
    }

    private static class TestClient extends RestClient<State<Response, ?>, Request, Response, TestCookie> {
        public TestClient(
                ReaderT<Request, State<Response, ?>, Response> requestReader,
                Fn1<HttpUrl, List<TestCookie>> cookieFn) {

            super(requestReader, cookieFn);
        }
    }

    @Value
    private static class TestCookie {
        String value;
    }
}
