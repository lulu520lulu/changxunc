package com.mall.hangxunc.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CustomStyleBody implements Serializable {

    @SerializedName("sex")
    String sex;
    @SerializedName("age")
    String age;
    @SerializedName("interest")
    String interest;

    @SerializedName("industryId")
    String industryId;
    @SerializedName("companyName")
    String companyName;

    public CustomStyleBody(String sex, String age, String interest, String industryId, String companyName) {
        this.sex = sex;
        this.age = age;
        this.interest = interest;
        this.industryId = industryId;
        this.companyName = companyName;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
