package com.mall.hangxunc.pages.center.center;

import com.mall.hangxunc.bean.home.BaseBean;

public class CenterIsLoginBean extends BaseBean {

    /**
     * code : 0
     * msg : success
     * data : {"is_company":"1","username":"test name","singleUrl":"http://c.hangxunc.com/index.php?sco=T1RFPQ=","companyName":"测试企业","companyUrl":"http://d.hangxunc.com:8081/scocenter/#/?sco=T1RFPQ="}
     */

    private CenterIsLoginData data;

    public CenterIsLoginData getData() {
        return data;
    }

    public void setData(CenterIsLoginData data) {
        this.data = data;
    }

}
