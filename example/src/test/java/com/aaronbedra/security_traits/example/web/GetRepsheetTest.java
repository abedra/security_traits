package com.aaronbedra.security_traits.example.web;

import com.aaronbedra.web.traits.SecureCookies;
import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.aaronbedra.web.types.WebRequestTestSubject;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import okhttp3.Cookie;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.types.Hostname.hostname;
import static com.aaronbedra.web.types.WebRequestTestSubject.okHttpWebRequestTestSubject;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class,
            SecureCookies.class
    })
    public WebRequestTestSubject<IO<?>, Cookie> secureHeaders() {
        return okHttpWebRequestTestSubject(hostname("getrepsheet.com"));
    }
}
