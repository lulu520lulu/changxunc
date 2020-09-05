package com.mall.hangxunc.utils;

public enum CurrencyType {

    CHINESE("CNY"),
    ENGLISH("USD");

    private String currency;

    CurrencyType(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency == null ? "" : currency;
    }
}
