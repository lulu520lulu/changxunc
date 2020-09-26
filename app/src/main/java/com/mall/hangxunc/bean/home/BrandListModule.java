package com.mall.hangxunc.bean.home;


public class BrandListModule extends BaseBean{

    /**
     * data : {"breadcrumbs":[{"text":"<i class=\"fa fa-home\"><\/i>","href":"http://c.hangxunc.com/"},{"text":"品牌","href":"http://c.hangxunc.com/index.php?route=product/manufacturer"}],"categories":{"H":{"name":"H","manufacturer":[{"name":"HAPPYSPACE 欢乐空间","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=12"}]},"K":{"name":"K","manufacturer":[{"name":"KEENZ","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=13"}]}},"continue":"http://c.hangxunc.com/","href":"index.php?route=api/ioslink/getCategory"}
     */

    private BrandListBean data;

    public BrandListBean getData() {
        return data;
    }

    public void setData(BrandListBean data) {
        this.data = data;
    }

}
