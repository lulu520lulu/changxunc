package com.mall.hangxunc.bean.home;

public class IsLoginBean extends BaseBean {

    /**
     * code : 0
     * msg : success
     * data : {"is_company":"1","username":"test name","singleUrl":"http://c.hangxunc.com/index.php?sco=T1RFPQ=","companyName":"测试企业","companyUrl":"http://d.hangxunc.com:8081/scocenter/#/?sco=T1RFPQ="}
     */

    private IsLoginData data;

    public IsLoginData getData() {
        return data;
    }

    public void setData(IsLoginData data) {
        this.data = data;
    }

}
