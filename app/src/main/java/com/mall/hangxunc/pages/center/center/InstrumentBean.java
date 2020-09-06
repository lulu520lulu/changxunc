package com.mall.hangxunc.pages.center.center;


import java.util.List;

public class InstrumentBean {

    /**
     * title : 大型仪器共享
     * titleImgPath : http://d.hangxunc.com:8081/scocenter/static/index/instrument-title.png
     * link : http://d.hangxunc.com:8081/scocenter/#/pages/instrument/instrument'
     * detailsLink : http://d.hangxunc.com:8081/scocenter/#/pages/instrument/instrumentDetails?id=
     * list : null
     * products : [{"title":"检验检测设备","titleImgPath":"http://d.hangxunc.com:8081/scocenter/static/index/instrument-test.png","link":null,"detailsLink":null,"list":{"total":1,"list":[{"id":"1286679801705955330","uuid":null,"instrumentSortId":"1250740262338625538","name":"仪器仪器","manufacturer":"案说法为","spec":"问题","price":"0.2","priceCode":"1","priceRange":"0-1020","yyly":"fwel","zygn":"3快款可","jszb":"四的股份看看","remark":"撒旦发射点发","status":1,"updater":1067246875800000001,"updateDate":1595603344000,"instrumentSortName":"检验检测设备","imgUrlList":["img/20200724/c7f6ebb347ed4db1afa7e436744f0d3b.jpg"],"languageCode":"zh-CN"}]},"products":null},{"title":"研发设备","titleImgPath":"http://d.hangxunc.com:8081/scocenter/static/index/instrument-research.png","link":null,"detailsLink":null,"list":{"total":2,"list":[{"id":"1250989534506168321","uuid":null,"instrumentSortId":"1250740313492357121","name":"仪器2","manufacturer":"仪器2","spec":"仪器2","price":"999.2","priceCode":"2","priceRange":"仪器2","yyly":"仪器2","zygn":"仪器2","jszb":"仪器2","remark":"仪器2","status":1,"updater":1067246875800000001,"updateDate":1590387637000,"instrumentSortName":"研发设备","imgUrlList":["img/20200525/40fc3e99332d4447bfd98a0912882b58.jpg","img/20200525/e158353fffa74c83957f4923f033a53c.jpg","img/20200525/72ebb47923e7442d88341845bc94aa49.jpg","img/20200525/9907adc5c2cc48279169667c002922d7.jpg"],"languageCode":"zh-CN"},{"id":"1250989534506168322","uuid":null,"instrumentSortId":"1250740313492357121","name":"仪","manufacturer":"仪器2","spec":"仪器2","price":"999.2","priceCode":"2","priceRange":"仪器2","yyly":"仪器2","zygn":"仪器2","jszb":"仪器2","remark":"仪器2","status":1,"updater":1067246875800000001,"updateDate":1590387657000,"instrumentSortName":"研发设备","imgUrlList":["img/20200525/82616957b319488a9e4b36f9ec9fa918.jpg","img/20200525/5a3f99c18ee848f3b1565da22e06fb1f.jpg","img/20200525/5f15f53c22804cd0b5862a1d001b634d.jpg","img/20200525/3a23b175a93e428e97e30c2d912bd372.jpg"],"languageCode":"zh-CN"}]},"products":null}]
     */

    private String title;
    private String titleImgPath;
    private String link;
    private String detailsLink;
    private Object list;
    private List<InstrumentChildBean> products;

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

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public List<InstrumentChildBean> getProducts() {
        return products;
    }

    public void setProducts(List<InstrumentChildBean> products) {
        this.products = products;
    }

}
