package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureCookies;
import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class,
            SecureCookies.class
    })
    public WebRequester secureHeaders() {
        return new WebRequester("getrepsheet.com");
    }
}
