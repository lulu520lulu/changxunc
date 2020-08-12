package com.c.hangxunc.pages.shop;

public class MessageLocal {
    public static final String LANGUAGE_CHANGE = "language_change";
    public static final String CURRENCY_CHANGE = "currency_change";


    public final String message;

    public static MessageLocal getInstance(String message) {
        return new MessageLocal(message);
    }

    private MessageLocal(String message) {
        this.message = message;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
