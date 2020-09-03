package com.c.hangxunc.message;

public class MessageHome {

    public final String message;

    public static MessageHome getInstance(String message) {
        return new MessageHome(message);
    }

    private MessageHome(String message) {
        this.message = message;
    }
}
