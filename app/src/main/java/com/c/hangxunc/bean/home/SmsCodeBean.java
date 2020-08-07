package com.c.hangxunc.bean.home;

public class SmsCodeBean {

    /**
     * code : 0
     * smsCode : 585696
     * telephone : 111111111
     * time : 1596792770
     * status : success
     */

    private int code;
    private String smsCode;
    private String telephone;
    private int time;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SmsCodeBean{" +
                "code=" + code +
                ", smsCode='" + smsCode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
