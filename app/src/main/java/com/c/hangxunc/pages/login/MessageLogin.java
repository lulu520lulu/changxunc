package com.c.hangxunc.pages.login;


public class MessageLogin {
    public static final String LOGIN_IN = "login_in";
    public static final String LOGIN_OUT = "login_out";

    public static final String ADD_SHOP_SUCCESS = "add_shop_success";


    public final String message;

    public static MessageLogin getInstance(String message) {
        return new MessageLogin(message);
    }

    private MessageLogin(String message) {
        this.message = message;
    }
}
