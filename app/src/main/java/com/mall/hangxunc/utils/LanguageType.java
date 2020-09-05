package com.mall.hangxunc.utils;


public enum LanguageType {

    CHINESE("zh-cn"),
    ENGLISH("en-gb"),
    RU("ru-ru");

    private String language;

    LanguageType(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language == null ? "" : language;
    }
}
