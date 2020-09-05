package com.mall.hangxunc.bean.login;

import com.mall.hangxunc.bean.home.BaseBean;

public class SmsCodeData extends BaseBean {

    /**
     * code : 0
     * data : {"smsCode":245322,"telephone":"15621052858","time":1599061158}
     * msg : success
     */

    private SmsCodeBean data;


    public SmsCodeBean getData() {
        return data;
    }

    public void setData(SmsCodeBean data) {
        this.data = data;
    }


}
