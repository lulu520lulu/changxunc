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

    public CustomStyleBody( String sex, String age, String interest) {
        this.sex = sex;
        this.age = age;
        this.interest = interest;
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
