package com.mall.hangxunc.bean.guide;


import java.io.Serializable;
import java.util.List;

public class IndustyData implements Serializable {
    private List<IndustryBean> industry;

    public List<IndustryBean> getIndustry() {
        return industry;
    }

    public void setIndustry(List<IndustryBean> industry) {
        this.industry = industry;
    }


}
