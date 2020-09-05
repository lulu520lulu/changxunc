package com.mall.hangxunc.bean.login;

import com.mall.hangxunc.bean.home.BaseBean;

public class LoginData extends BaseBean {

    /**
     * code : 0
     * data : {"sco":"","session_id":"931a768203b6175fa680812e05"}
     * msg : SUCCESS
     */

    private LoginInfo data;


    public LoginInfo getData() {
        return data;
    }

    public void setData(LoginInfo data) {
        this.data = data;
    }

}
