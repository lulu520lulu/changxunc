package com.c.hangxunc.bean.home;

public class LimitsBean {
    /**
     * text : 20
     * value : 20
     * href : http://c.hangxunc.com/index.php?route=product/search&search=%E9%9E%8B&limit=20
     */

    private int text;
    private int value;
    private String href;

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
