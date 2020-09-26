package com.mall.hangxunc.bean.event;

/**
 * 引导页-返回选择性别页面
 * Created by 王新超 on 2020/9/5.
 */
public class GuideBackEvent {
    private int index;

    public GuideBackEvent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
