package com.c.hangxunc.bean.home;

import java.util.List;

public class CurrencyListBean {

    /**
     * action : http://b.hangxunc.com/index.php?route=common/currency/currency
     * code : CNY
     * currencies : [{"title":"US Dollar","code":"USD","symbol_left":"$","symbol_right":"","href":"http://b.hangxunc.com/index.php?route=api/ioslink/getCurrency&amp;currency=USD"},{"title":"人民币","code":"CNY","symbol_left":"￥","symbol_right":"","href":"http://b.hangxunc.com/index.php?route=api/ioslink/getCurrency&amp;currency=CNY"}]
     */

    private String action;
    private String code;
    private List<CurrencyBean> currencies;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CurrencyBean> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyBean> currencies) {
        this.currencies = currencies;
    }

}
