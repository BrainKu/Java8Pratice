package com.kuxinwei.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by brainku on 17/8/5.
 */
public class Chap4Test {

    @Test
    public void defaultMethod() {
        Parent parent = new ParentIml();
        parent.welcome();
        Assert.assertEquals(Parent.MSG, parent.getLassMessage());
    }

    @Test
    public void optionalObject() {
        Optional<String> empty = Optional.empty();
        Assert.assertEquals("hello null", empty.orElseGet(() -> "hello null"));
        Assert.assertEquals("hello null", empty.orElse("hello null"));
    }

    public interface Parent {
        String MSG = "Parent: Hi";
        void message(String body);

        default void welcome() {
            message(MSG);
        }

        String getLassMessage();
    }

    private class ParentIml implements Parent {
        String msg;
        @Override
        public void message(String body) {
            System.out.println(body);
            msg = body;
        }

        @Override
        public String getLassMessage() {
            return msg;
        }
    }
}
