package com.c.hangxunc.pages;

public class MessageLocal {
    public static final String CHANGE = "change";

    public final String message;

    public static MessageLocal getInstance(String message) {
        return new MessageLocal(message);
    }

    private MessageLocal(String message) {
        this.message = message;
    }
}
