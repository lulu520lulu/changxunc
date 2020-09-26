package com.mall.hangxunc.bean.event;

public class GuideInterestNextEvent {
    private String interest;

    public GuideInterestNextEvent(String interest) {
        this.interest = interest;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}

