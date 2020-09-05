package com.mall.hangxunc.bean.login;

public class SmsCodeBean {
    /**
     * smsCode : 245322
     * telephone : 15621052858
     * time : 1599061158
     */

    private String  smsCode;
    private String telephone;
    private int time;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
