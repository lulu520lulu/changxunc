package com.mall.hangxunc.pages.street.http;


import com.mall.hangxunc.bean.home.BaseBean;

public class StreetIsLoginBean extends BaseBean {

    /**
     * code : 0
     * msg : success
     * data : {"is_company":"1","username":"test name","singleUrl":"http://c.hangxunc.com/index.php?sco=T1RFPQ=","companyName":"测试企业","companyUrl":"http://d.hangxunc.com:8081/scocenter/#/?sco=T1RFPQ="}
     */

    private StreetIsLoginData data;

    public StreetIsLoginData getData() {
        return data;
    }

    public void setData(StreetIsLoginData data) {
        this.data = data;
    }

}
