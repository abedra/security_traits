package com.aaronbedra.password;

import com.jnape.palatable.lambda.adt.hlist.Tuple2;

import java.util.ArrayList;

import static com.jnape.palatable.lambda.functions.builtin.fn2.Tupler2.tupler;

public class CharacterRange {
    public static final ArrayList<Character> ALL = characterRange(new ArrayList<>() {{
        add(tupler(1, 128));
    }});

    public static final ArrayList<Character> NUMBER = characterRange(new ArrayList<>() {{
        add(tupler(48, 57));
    }});

    public static final ArrayList<Character> UPPER = characterRange(new ArrayList<>() {{
        add(tupler(65, 90));
    }});

    public static final ArrayList<Character> LOWER = characterRange(new ArrayList<>() {{
        add(tupler(97, 122));
    }});

    public static final ArrayList<Character> SPECIAL = characterRange(new ArrayList<>() {{
        add(tupler(37, 47));
        add(tupler(58, 64));
        add(tupler(91, 96));
        add(tupler(123, 126));
    }});

    private static ArrayList<Character> characterRange(ArrayList<Tuple2<Integer, Integer>> ranges) {
        ArrayList<Character> chars = new ArrayList<>();

        for (Tuple2<Integer, Integer> range : ranges) {
            for (int i = range._1(); i < range._2(); i++) {
                chars.add((char)i);
            }
        }

        return chars;
    }
}
