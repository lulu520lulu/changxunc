package com.c.hangxunc.message;

public class MessageGoLogin {
    public static final String GO_LOGIN = "go_login";

    public final String message;

    public static MessageGoLogin getInstance(String message) {
        return new MessageGoLogin(message);
    }

    public MessageGoLogin(String message) {
        this.message = message;
    }
}
