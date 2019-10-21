package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import okhttp3.Headers;
import okhttp3.Response;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.Requester.requester;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits(SecureHeaders.class)
    public IO<Headers> secureHeaders() {
        return requester("https://getrepsheet.com").getHeadersIO();
    }

    @TestTraits(SecureRedirect.class)
    public IO<Response> secureRedirect() {
        return requester("http://getrepsheet.com").getResponseIO();
    }
}
