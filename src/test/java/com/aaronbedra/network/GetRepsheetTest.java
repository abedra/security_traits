package com.aaronbedra.network;

import com.aaronbedra.network.traits.HasOpenPorts;
import com.jnape.palatable.lambda.adt.hlist.Tuple2;
import com.jnape.palatable.lambda.io.IO;
import com.jnape.palatable.traitor.annotations.TestTraits;
import com.jnape.palatable.traitor.runners.Traits;
import org.junit.runner.RunWith;

import java.util.List;

import static com.aaronbedra.network.Requester.requester;
import static com.jnape.palatable.lambda.functions.builtin.fn2.Tupler2.tupler;
import static java.util.Arrays.asList;

@RunWith(Traits.class)
public class GetRepsheetTest {
    @TestTraits(HasOpenPorts.class)
    public Tuple2<IO<Requester>, List<Integer>> openPorts() {
        return tupler(requester("getrepsheet.com"), asList(80, 443));
    }
}
