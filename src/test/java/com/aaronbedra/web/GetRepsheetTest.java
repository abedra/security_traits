package com.aaronbedra.web;

import com.aaronbedra.web.traits.SecureHeaders;
import com.aaronbedra.web.traits.SecureRedirect;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import static com.aaronbedra.web.Requester.requesterIO;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits({
            SecureHeaders.class,
            SecureRedirect.class
    })
    public IO<Requester> secureHeaders() {
        return requesterIO("getrepsheet.com");
    }
}
