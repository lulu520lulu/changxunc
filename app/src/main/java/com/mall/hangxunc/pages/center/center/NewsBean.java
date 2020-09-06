package com.mall.hangxunc.pages.center.center;

public class NewsBean {
    /**
     * title : 上合热点
     * titleImgPath : http://d.hangxunc.com:8081/scocenter/static/index/spot-title.png
     * link : http://d.hangxunc.com:8081/scocenter/#/pages/news/news
     * detailsLink : http://d.hangxunc.com:8081/scocenter/#/pages/details/details?id=
     * list : {"total":5,"list":[{"id":"1286714101772013570","uuid":"","newsTypeId":1252517614462074881,"newsName":"asdfas","newsBrief":"sdfsdf","description":"<p>asdfa<img src=\"img/20200725/84343a0a81784701a04bbe713f3f5e7e.jpg\"><\/p>","hitnum":1111,"commentnum":12,"isPub":1,"imgUuid":"img/20200725/9a2bd81c5fbc4af8b619b07b1300b4bf.jpg","newstime":"2020-07-24","status":1,"updateDate":1595612022000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1262607974504820737","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻1","newsBrief":"新闻11","description":"<p>新闻2新闻2<img src=\"img/20200519/741a7bc0e9104439bf57c7444118584c.jpg\"><\/p>","hitnum":2,"commentnum":2,"isPub":1,"imgUuid":"img/20200519/146ea255215b464f8faa27c9bd05b3b8.jpg","newstime":"2020-06-03","status":1,"updateDate":1589866789000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1263034893675208706","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻2","newsBrief":"新闻22","description":"<p>新闻5新闻5新闻5<\/p>","hitnum":22,"commentnum":55,"isPub":1,"imgUuid":null,"newstime":"2020-05-20","status":1,"updateDate":1589965959000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1263034892320448514","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻3","newsBrief":"新闻33","description":"<p>新闻5新闻5新闻5<\/p>","hitnum":22,"commentnum":55,"isPub":1,"imgUuid":null,"newstime":"2020-05-20","status":1,"updateDate":1589965959000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"}]}
     * products : null
     */

    private String title;
    private String titleImgPath;
    private String link;
    private String detailsLink;
    private NewsChildBean list;
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

    public NewsChildBean getList() {
        return list;
    }

    public void setList(NewsChildBean list) {
        this.list = list;
    }

    public Object getProducts() {
        return products;
    }

    public void setProducts(Object products) {
        this.products = products;
    }


}
