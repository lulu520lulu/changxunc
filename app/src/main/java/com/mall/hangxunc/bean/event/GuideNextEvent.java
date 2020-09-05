package com.mall.hangxunc.bean.event;

/**
 * 导航页点击下一步
 * Created by 王新超 on 2020/9/5.
 */
public class GuideNextEvent {
    private String selectAge;
    private String selectSex;

    public GuideNextEvent(String selectSex, String selectAge) {
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
