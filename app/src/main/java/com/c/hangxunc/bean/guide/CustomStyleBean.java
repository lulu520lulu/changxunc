package com.c.hangxunc.bean.guide;

import java.io.Serializable;
import java.util.List;

public class CustomStyleBean implements Serializable {
    private SexBean sex;
    private List<TimeBean> time;
    private List<InterestBean> interest;

    public SexBean getSex() {
        return sex;
    }

    public void setSex(SexBean sex) {
        this.sex = sex;
    }

    public List<TimeBean> getTime() {
        return time;
    }

    public void setTime(List<TimeBean> time) {
        this.time = time;
    }

    public List<InterestBean> getInterest() {
        return interest;
    }

    public void setInterest(List<InterestBean> interest) {
        this.interest = interest;
    }

}
