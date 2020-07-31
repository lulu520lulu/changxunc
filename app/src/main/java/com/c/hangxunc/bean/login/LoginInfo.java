package com.c.hangxunc.bean.login;

public class LoginInfo {

    /**
     * customer_id : 15
     * session_id : e3c59c40727dd29a415ab7a88d
     * shipping_address : {"address_id":3,"firstname":"test","lastname":"T","calling_code":"86","telephone":"13333333333","company":"test","address_1":"test1","address_2":"test2","postcode":"273500","zone_id":707,"zone":"山东省","zone_code":"SA","country_id":44,"country":"中国","iso_code_2":"CN","iso_code_3":"CHN","address_format":"","city_id":2346,"city":"青岛市","county_id":2351,"county":"市北区","custom_field":null}
     * payment_address : null
     */

    private String customer_id;
    private String session_id;
    private ShippingAddressBean shipping_address;
    private Object payment_address;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public ShippingAddressBean getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(ShippingAddressBean shipping_address) {
        this.shipping_address = shipping_address;
    }

    public Object getPayment_address() {
        return payment_address;
    }

    public void setPayment_address(Object payment_address) {
        this.payment_address = payment_address;
    }

    public static class ShippingAddressBean {
        /**
         * address_id : 3
         * firstname : test
         * lastname : T
         * calling_code : 86
         * telephone : 13333333333
         * company : test
         * address_1 : test1
         * address_2 : test2
         * postcode : 273500
         * zone_id : 707
         * zone : 山东省
         * zone_code : SA
         * country_id : 44
         * country : 中国
         * iso_code_2 : CN
         * iso_code_3 : CHN
         * address_format :
         * city_id : 2346
         * city : 青岛市
         * county_id : 2351
         * county : 市北区
         * custom_field : null
         */

        private int address_id;
        private String firstname;
        private String lastname;
        private String calling_code;
        private String telephone;
        private String company;
        private String address_1;
        private String address_2;
        private String postcode;
        private int zone_id;
        private String zone;
        private String zone_code;
        private int country_id;
        private String country;
        private String iso_code_2;
        private String iso_code_3;
        private String address_format;
        private int city_id;
        private String city;
        private int county_id;
        private String county;
        private Object custom_field;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getCalling_code() {
            return calling_code;
        }

        public void setCalling_code(String calling_code) {
            this.calling_code = calling_code;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public int getZone_id() {
            return zone_id;
        }

        public void setZone_id(int zone_id) {
            this.zone_id = zone_id;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getZone_code() {
            return zone_code;
        }

        public void setZone_code(String zone_code) {
            this.zone_code = zone_code;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIso_code_2() {
            return iso_code_2;
        }

        public void setIso_code_2(String iso_code_2) {
            this.iso_code_2 = iso_code_2;
        }

        public String getIso_code_3() {
            return iso_code_3;
        }

        public void setIso_code_3(String iso_code_3) {
            this.iso_code_3 = iso_code_3;
        }

        public String getAddress_format() {
            return address_format;
        }

        public void setAddress_format(String address_format) {
            this.address_format = address_format;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCounty_id() {
            return county_id;
        }

        public void setCounty_id(int county_id) {
            this.county_id = county_id;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public Object getCustom_field() {
            return custom_field;
        }

        public void setCustom_field(Object custom_field) {
            this.custom_field = custom_field;
        }
    }
}
