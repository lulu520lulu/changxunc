package com.c.hangxunc.pages.shop;

public class MessageShop {

    public static final String ADD_SHOP_SUCCESS = "add_shop_success";

    public final String message;

    public static MessageShop getInstance(String message) {
        return new MessageShop(message);
    }

    private MessageShop(String message) {
        this.message = message;
    }
}
