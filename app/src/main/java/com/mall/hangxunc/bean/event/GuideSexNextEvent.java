package com.mall.hangxunc.bean.event;


public class GuideSexNextEvent {
    private String selectAge;
    private String selectSex;

    public GuideSexNextEvent(String selectSex, String selectAge) {
        this.selectSex = selectSex;
        this.selectAge = selectAge;
    }

    public String getSelectAge() {
        return selectAge;
    }

    public void setSelectAge(String selectAge) {
        this.selectAge = selectAge;
    }

    public String getSelectSex() {
        return selectSex;
    }

    public void setSelectSex(String selectSex) {
        this.selectSex = selectSex;
    }
}
