package com.mall.hangxunc.bean.home;

public class SortsBean {
    /**
     * text : 默认
     * value : p.sort_order-ASC
     * href : http://c.hangxunc.com/index.php?route=product/search&sort=p.sort_order&order=ASC&search=%E9%9E%8B
     */

    private String text;
    private String value;
    private String href;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
