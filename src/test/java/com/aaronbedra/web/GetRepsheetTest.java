package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureCookies;
import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.WebRequester.requester;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class,
            SecureCookies.class
    })
    public IO<WebRequester> secureHeaders() {
        return requester("getrepsheet.com");
    }
}
