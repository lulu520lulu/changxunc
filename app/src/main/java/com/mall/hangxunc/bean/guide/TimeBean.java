package com.mall.hangxunc.bean.guide;

import java.io.Serializable;

public class TimeBean implements Serializable {
    /**
     * stage : 10后
     */

    private String stage;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}

