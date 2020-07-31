package com.c.hangxunc.bean.home;

public class CurrencyBean {
    /**
     * title : US Dollar
     * code : USD
     * symbol_left : $
     * symbol_right :
     * href : http://b.hangxunc.com/index.php?route=api/ioslink/getCurrency&amp;currency=USD
     */

    private String title;
    private String code;
    private String symbol_left;
    private String symbol_right;
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol_left() {
        return symbol_left;
    }

    public void setSymbol_left(String symbol_left) {
        this.symbol_left = symbol_left;
    }

    public String getSymbol_right() {
        return symbol_right;
    }

    public void setSymbol_right(String symbol_right) {
        this.symbol_right = symbol_right;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "CurrenciesBean{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", symbol_left='" + symbol_left + '\'' +
                ", symbol_right='" + symbol_right + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
