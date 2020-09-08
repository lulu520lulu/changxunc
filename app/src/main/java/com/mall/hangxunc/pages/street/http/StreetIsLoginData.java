package com.mall.hangxunc.pages.street.http;

public class StreetIsLoginData {
    /**
     * is_company : 1
     * username : test name
     * singleUrl : http://c.hangxunc.com/index.php?sco=T1RFPQ=
     * companyName : 测试企业
     * companyUrl : http://d.hangxunc.com:8081/scocenter/#/?sco=T1RFPQ=
     */

    private int is_company;
    private String username;
    private String singleUrl;
    private String companyName;
    private String companyUrl;

    public int getIs_company() {
        return is_company;
    }

    public void setIs_company(int is_company) {
        this.is_company = is_company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSingleUrl() {
        return singleUrl;
    }

    public void setSingleUrl(String singleUrl) {
        this.singleUrl = singleUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

}
