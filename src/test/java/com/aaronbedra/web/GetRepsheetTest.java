package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureHeaders;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import okhttp3.Headers;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.Requester.requester;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits(SecureHeaders.class)
    public IO<Headers> getRepsheet() {
        return requester("https://getrepsheet.com").getHeadersIO();
    }
}
