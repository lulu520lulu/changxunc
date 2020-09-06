package com.mall.hangxunc.pages.center.center;


public class XqkBean {
    /**
     * title : 最新技术需求
     * titleImgPath : http://d.hangxunc.com:8081/scocenter/static/index/requirement-title.png
     * link : http://d.hangxunc.com:8081/scocenter/#/pages/xqk/xqk
     * detailsLink : http://d.hangxunc.com:8081/scocenter/#/pages/xqk/xqkDetail?id=
     * list : {"total":14,"list":[{"id":1286974713844375554,"uuid":null,"title":"qwe","intro":"qweq","detail":"qweqe","industryId":1268723095199854594,"typeCode":"xq_first","status":3,"creator":1286971830562394114,"createDate":1595673657000,"updater":1286971830562394114,"updateDate":1595673657000,"countryId":110101,"remark":"123123","budget":122,"deadline":"2020-07-31","demander":"1213","creator_tel":null,"creator_email":null,"industryName":"需求库1","creatorName":null,"typeName":"需求1","parentCountryName":"北京市","parentCountryId":110100,"statusStr":"待解决","tempId":"1286974713844375554","tempCreatorId":null,"languageCode":"zh-CN","countryName":"东城区"},{"id":1278600120240140290,"uuid":null,"title":"测试名称","intro":"测试简介","detail":"测试详情","industryId":1268723258651881474,"typeCode":"xq_secend","status":3,"creator":1265916853003816961,"createDate":1593676998000,"updater":1067246875800000001,"updateDate":1595605419000,"countryId":370202,"remark":"测试备注","budget":300000,"deadline":"2020-07-08","demander":"测试联系人","creator_tel":null,"creator_email":null,"industryName":"需求库2","creatorName":null,"typeName":"需求2","parentCountryName":"青岛市","parentCountryId":370200,"statusStr":"待解决","tempId":"1278600120240140290","tempCreatorId":null,"languageCode":"zh-CN","countryName":"市南区"},{"id":1278886122800168962,"uuid":null,"title":"发布测试","intro":"发布测试","detail":"发布测试","industryId":1268723095199854594,"typeCode":"xq_first","status":3,"creator":1265916853003816961,"createDate":1593745186000,"updater":1067246875800000001,"updateDate":1595605397000,"countryId":10000000000,"remark":"发布测试","budget":159753,"deadline":"2022-07-03","demander":"发布测试","creator_tel":null,"creator_email":null,"industryName":"需求库1","creatorName":null,"typeName":"需求1","parentCountryName":"","parentCountryId":0,"statusStr":"待解决","tempId":"1278886122800168962","tempCreatorId":null,"languageCode":"zh-CN","countryName":"乌兹别克斯坦"},{"id":1286686825688813569,"uuid":"","title":"sgwesdfsdf","intro":"sfdgsdg","detail":"sdger","industryId":1251121525781540865,"typeCode":"xq_first","status":3,"creator":1067246875800000001,"createDate":1595605019000,"updater":1067246875800000001,"updateDate":1595605039000,"countryId":10000000,"remark":"ert34","budget":23423,"deadline":"2020-07-29","demander":"234234","creator_tel":null,"creator_email":null,"industryName":"冷库街口","creatorName":null,"typeName":"需求1","parentCountryName":"","parentCountryId":0,"statusStr":"待解决","tempId":"1286686825688813569","tempCreatorId":null,"languageCode":"zh-CN","countryName":"哈萨克斯坦"}]}
     * products : null
     */

    private String title;
    private String titleImgPath;
    private String link;
    private String detailsLink;
    private XqkChildBean list;
    private Object products;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImgPath() {
        return titleImgPath;
    }

    public void setTitleImgPath(String titleImgPath) {
        this.titleImgPath = titleImgPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public XqkChildBean getList() {
        return list;
    }

    public void setList(XqkChildBean list) {
        this.list = list;
    }

    public Object getProducts() {
        return products;
    }

    public void setProducts(Object products) {
        this.products = products;
    }

}
