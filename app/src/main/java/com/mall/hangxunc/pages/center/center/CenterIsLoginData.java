package com.mall.hangxunc.pages.center.center;

import com.google.gson.annotations.SerializedName;

public class CenterIsLoginData {

    /**
     * 2bLink : http://b.hangxunc.com/
     * 2cLink : http://c.hangxunc.com/
     * enterpriseLink : http://b.hangxunc.com//index.php?route=account/company&sco=TkRjPQ==&footer=hide
     * user : {"id":1300991181427232800,"creator":null,"createDate":1599015443000,"customerId":"47","username":"aa@qq.com","enterpriseName":null,"password":"$2a$10$/uzIXfm5AUdJUa6yj/KEt.pI8yiTEciP6ICs1gORpsOTLIdq.UBh2","realName":"aa@qq.com","headUrl":"","gender":2,"email":"aa@qq.com","mobile":"aa@qq.com","deptId":null,"enterpriseStatus":0,"superAdmin":0,"status":1,"updater":null,"updateDate":1599015443000,"deptName":null,"userTypeDict":"usertype_personal","userTypeValue":null,"userId":"1300991181427232769"}
     */

    @SerializedName("2bLink")
    private String bLink;
    @SerializedName("2cLink")
    private String cLink;
    private String enterpriseLink;
    private UserBean user;

    public String getbLink() {
        return bLink;
    }

    public void setbLink(String bLink) {
        this.bLink = bLink;
    }

    public String getcLink() {
        return cLink;
    }

    public void setcLink(String cLink) {
        this.cLink = cLink;
    }

    public String getEnterpriseLink() {
        return enterpriseLink;
    }

    public void setEnterpriseLink(String enterpriseLink) {
        this.enterpriseLink = enterpriseLink;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 1300991181427232800
         * creator : null
         * createDate : 1599015443000
         * customerId : 47
         * username : aa@qq.com
         * enterpriseName : null
         * password : $2a$10$/uzIXfm5AUdJUa6yj/KEt.pI8yiTEciP6ICs1gORpsOTLIdq.UBh2
         * realName : aa@qq.com
         * headUrl :
         * gender : 2
         * email : aa@qq.com
         * mobile : aa@qq.com
         * deptId : null
         * enterpriseStatus : 0
         * superAdmin : 0
         * status : 1
         * updater : null
         * updateDate : 1599015443000
         * deptName : null
         * userTypeDict : usertype_personal
         * userTypeValue : null
         * userId : 1300991181427232769
         */

        private long id;
        private Object creator;
        private long createDate;
        private String customerId;
        private String username;
        private Object enterpriseName;
        private String password;
        private String realName;
        private String headUrl;
        private int gender;
        private String email;
        private String mobile;
        private Object deptId;
        private int enterpriseStatus;
        private int superAdmin;
        private int status;
        private Object updater;
        private long updateDate;
        private Object deptName;
        private String userTypeDict;
        private Object userTypeValue;
        private String userId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getCreator() {
            return creator;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(Object enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public int getEnterpriseStatus() {
            return enterpriseStatus;
        }

        public void setEnterpriseStatus(int enterpriseStatus) {
            this.enterpriseStatus = enterpriseStatus;
        }

        public int getSuperAdmin() {
            return superAdmin;
        }

        public void setSuperAdmin(int superAdmin) {
            this.superAdmin = superAdmin;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getUpdater() {
            return updater;
        }

        public void setUpdater(Object updater) {
            this.updater = updater;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public Object getDeptName() {
            return deptName;
        }

        public void setDeptName(Object deptName) {
            this.deptName = deptName;
        }

        public String getUserTypeDict() {
            return userTypeDict;
        }

        public void setUserTypeDict(String userTypeDict) {
            this.userTypeDict = userTypeDict;
        }

        public Object getUserTypeValue() {
            return userTypeValue;
        }

        public void setUserTypeValue(Object userTypeValue) {
            this.userTypeValue = userTypeValue;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
