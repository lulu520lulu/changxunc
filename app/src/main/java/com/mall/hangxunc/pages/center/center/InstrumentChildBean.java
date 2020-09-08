package com.mall.hangxunc.pages.center.center;

import java.util.List;

public class InstrumentChildBean {
    /**
     * title : 检验检测设备
     * titleImgPath : http://d.hangxunc.com:8081/scocenter/static/index/instrument-test.png
     * link : null
     * detailsLink : null
     * list : {"total":1,"list":[{"id":"1286679801705955330","uuid":null,"instrumentSortId":"1250740262338625538","name":"仪器仪器","manufacturer":"案说法为","spec":"问题","price":"0.2","priceCode":"1","priceRange":"0-1020","yyly":"fwel","zygn":"3快款可","jszb":"四的股份看看","remark":"撒旦发射点发","status":1,"updater":1067246875800000001,"updateDate":1595603344000,"instrumentSortName":"检验检测设备","imgUrlList":["img/20200724/c7f6ebb347ed4db1afa7e436744f0d3b.jpg"],"languageCode":"zh-CN"}]}
     * products : null
     */

    private String title;
    private String titleImgPath;
    private Object link;
    private Object detailsLink;
    private ListBean list;
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

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public Object getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(Object detailsLink) {
        this.detailsLink = detailsLink;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public Object getProducts() {
        return products;
    }

    public void setProducts(Object products) {
        this.products = products;
    }

    public static class ListBean {
        /**
         * total : 1
         * list : [{"id":"1286679801705955330","uuid":null,"instrumentSortId":"1250740262338625538","name":"仪器仪器","manufacturer":"案说法为","spec":"问题","price":"0.2","priceCode":"1","priceRange":"0-1020","yyly":"fwel","zygn":"3快款可","jszb":"四的股份看看","remark":"撒旦发射点发","status":1,"updater":1067246875800000001,"updateDate":1595603344000,"instrumentSortName":"检验检测设备","imgUrlList":["img/20200724/c7f6ebb347ed4db1afa7e436744f0d3b.jpg"],"languageCode":"zh-CN"}]
         */

        private int total;
        private List<ListChildBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListChildBean> getList() {
            return list;
        }

        public void setList(List<ListChildBean> list) {
            this.list = list;
        }

        public static class ListChildBean {
            /**
             * id : 1286679801705955330
             * uuid : null
             * instrumentSortId : 1250740262338625538
             * name : 仪器仪器
             * manufacturer : 案说法为
             * spec : 问题
             * price : 0.2
             * priceCode : 1
             * priceRange : 0-1020
             * yyly : fwel
             * zygn : 3快款可
             * jszb : 四的股份看看
             * remark : 撒旦发射点发
             * status : 1
             * updater : 1067246875800000001
             * updateDate : 1595603344000
             * instrumentSortName : 检验检测设备
             * imgUrlList : ["img/20200724/c7f6ebb347ed4db1afa7e436744f0d3b.jpg"]
             * languageCode : zh-CN
             */

            private String id;
            private Object uuid;
            private String instrumentSortId;
            private String name;
            private String manufacturer;
            private String spec;
            private String price;
            private String priceCode;
            private String priceRange;
            private String yyly;
            private String zygn;
            private String jszb;
            private String remark;
            private int status;
            private long updater;
            private long updateDate;
            private String instrumentSortName;
            private String languageCode;
            private List<String> imgUrlList;
            private String detailsLink;

            public String getDetailsLink() {
                return detailsLink;
            }

            public void setDetailsLink(String detailsLink) {
                this.detailsLink = detailsLink;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getUuid() {
                return uuid;
            }

            public void setUuid(Object uuid) {
                this.uuid = uuid;
            }

            public String getInstrumentSortId() {
                return instrumentSortId;
            }

            public void setInstrumentSortId(String instrumentSortId) {
                this.instrumentSortId = instrumentSortId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPriceCode() {
                return priceCode;
            }

            public void setPriceCode(String priceCode) {
                this.priceCode = priceCode;
            }

            public String getPriceRange() {
                return priceRange;
            }

            public void setPriceRange(String priceRange) {
                this.priceRange = priceRange;
            }

            public String getYyly() {
                return yyly;
            }

            public void setYyly(String yyly) {
                this.yyly = yyly;
            }

            public String getZygn() {
                return zygn;
            }

            public void setZygn(String zygn) {
                this.zygn = zygn;
            }

            public String getJszb() {
                return jszb;
            }

            public void setJszb(String jszb) {
                this.jszb = jszb;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getUpdater() {
                return updater;
            }

            public void setUpdater(long updater) {
                this.updater = updater;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public String getInstrumentSortName() {
                return instrumentSortName;
            }

            public void setInstrumentSortName(String instrumentSortName) {
                this.instrumentSortName = instrumentSortName;
            }

            public String getLanguageCode() {
                return languageCode;
            }

            public void setLanguageCode(String languageCode) {
                this.languageCode = languageCode;
            }

            public List<String> getImgUrlList() {
                return imgUrlList;
            }

            public void setImgUrlList(List<String> imgUrlList) {
                this.imgUrlList = imgUrlList;
            }
        }
    }

}
