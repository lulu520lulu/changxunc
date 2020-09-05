package com.mall.hangxunc.bean.guide;

import com.mall.hangxunc.bean.home.BaseBean;


public class CustomStyleData extends BaseBean {

    /**
     * data : {"sex":{"man":{"man":"男士","image":"http://c.hangxunc.com/image/catalog/app/user-style/man-img.png"},"woman":{"woman":"女士","image":"http://c.hangxunc.com/image/catalog/app/user-style/woman.png"}},"time":[{"stage":"10后"},{"stage":"00后"},{"stage":"95后"},{"stage":"90后"},{"stage":"80后"},{"stage":"70后"},{"stage":"60后"},{"stage":"50后"}],"interest":[{"name":"健康","image":"http://c.hangxunc.com/image/catalog/app/user-style/jk.png"},{"name":"萌宠","image":"http://c.hangxunc.com/image/catalog/app/user-style/mc.png"},{"name":"实用清洁","image":"http://c.hangxunc.com/image/catalog/app/user-style/syqj.png"},{"name":"吃货必备","image":"http://c.hangxunc.com/image/catalog/app/user-style/chbb.png"},{"name":"生活用品","image":"http://c.hangxunc.com/image/catalog/app/user-style/shyp.png"},{"name":"居家穿搭","image":"http://c.hangxunc.com/image/catalog/app/user-style/jjcd.png"},{"name":"速食清单","image":"http://c.hangxunc.com/image/catalog/app/user-style/ssqd.png"},{"name":"呵护口腔","image":"http://c.hangxunc.com/image/catalog/app/user-style/hhkq.png"},{"name":"家电数码","image":"http://c.hangxunc.com/image/catalog/app/user-style/jdsm.png"},{"name":"厨房必备","image":"http://c.hangxunc.com/image/catalog/app/user-style/cfbb.png"},{"name":"家具家装","image":"http://c.hangxunc.com/image/catalog/app/user-style/jjjz.png"},{"name":"饮品冲调","image":"http://c.hangxunc.com/image/catalog/app/user-style/ypct.png"}]}
     */

    private CustomStyleBean data;

    public CustomStyleBean getData() {
        return data;
    }

    public void setData(CustomStyleBean data) {
        this.data = data;
    }

}
