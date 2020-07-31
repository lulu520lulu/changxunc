package com.c.hangxunc.bean.home;

public class SmsCodeBean {

    private int code;
    private String status;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SmsCodeBean{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
