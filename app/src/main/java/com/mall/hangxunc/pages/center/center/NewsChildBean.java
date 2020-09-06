package com.mall.hangxunc.pages.center.center;

import java.util.List;

public class NewsChildBean {
    /**
     * total : 5
     * list : [{"id":"1286714101772013570","uuid":"","newsTypeId":1252517614462074881,"newsName":"asdfas","newsBrief":"sdfsdf","description":"<p>asdfa<img src=\"img/20200725/84343a0a81784701a04bbe713f3f5e7e.jpg\"><\/p>","hitnum":1111,"commentnum":12,"isPub":1,"imgUuid":"img/20200725/9a2bd81c5fbc4af8b619b07b1300b4bf.jpg","newstime":"2020-07-24","status":1,"updateDate":1595612022000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1262607974504820737","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻1","newsBrief":"新闻11","description":"<p>新闻2新闻2<img src=\"img/20200519/741a7bc0e9104439bf57c7444118584c.jpg\"><\/p>","hitnum":2,"commentnum":2,"isPub":1,"imgUuid":"img/20200519/146ea255215b464f8faa27c9bd05b3b8.jpg","newstime":"2020-06-03","status":1,"updateDate":1589866789000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1263034893675208706","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻2","newsBrief":"新闻22","description":"<p>新闻5新闻5新闻5<\/p>","hitnum":22,"commentnum":55,"isPub":1,"imgUuid":null,"newstime":"2020-05-20","status":1,"updateDate":1589965959000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"},{"id":"1263034892320448514","uuid":"","newsTypeId":1252517614462074881,"newsName":"新闻3","newsBrief":"新闻33","description":"<p>新闻5新闻5新闻5<\/p>","hitnum":22,"commentnum":55,"isPub":1,"imgUuid":null,"newstime":"2020-05-20","status":1,"updateDate":1589965959000,"updater":1067246875800000001,"creator":1067246875800000001,"createName":null,"newsTypeName":"国内","languageCode":"zh-CN"}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1286714101772013570
         * uuid :
         * newsTypeId : 1252517614462074881
         * newsName : asdfas
         * newsBrief : sdfsdf
         * description : <p>asdfa<img src="img/20200725/84343a0a81784701a04bbe713f3f5e7e.jpg"></p>
         * hitnum : 1111
         * commentnum : 12
         * isPub : 1
         * imgUuid : img/20200725/9a2bd81c5fbc4af8b619b07b1300b4bf.jpg
         * newstime : 2020-07-24
         * status : 1
         * updateDate : 1595612022000
         * updater : 1067246875800000001
         * creator : 1067246875800000001
         * createName : null
         * newsTypeName : 国内
         * languageCode : zh-CN
         */

        private String id;
        private String uuid;
        private long newsTypeId;
        private String newsName;
        private String newsBrief;
        private String description;
        private int hitnum;
        private int commentnum;
        private int isPub;
        private String imgUuid;
        private String newstime;
        private int status;
        private long updateDate;
        private long updater;
        private long creator;
        private Object createName;
        private String newsTypeName;
        private String languageCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public long getNewsTypeId() {
            return newsTypeId;
        }

        public void setNewsTypeId(long newsTypeId) {
            this.newsTypeId = newsTypeId;
        }

        public String getNewsName() {
            return newsName;
        }

        public void setNewsName(String newsName) {
            this.newsName = newsName;
        }

        public String getNewsBrief() {
            return newsBrief;
        }

        public void setNewsBrief(String newsBrief) {
            this.newsBrief = newsBrief;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getHitnum() {
            return hitnum;
        }

        public void setHitnum(int hitnum) {
            this.hitnum = hitnum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public int getIsPub() {
            return isPub;
        }

        public void setIsPub(int isPub) {
            this.isPub = isPub;
        }

        public String getImgUuid() {
            return imgUuid;
        }

        public void setImgUuid(String imgUuid) {
            this.imgUuid = imgUuid;
        }

        public String getNewstime() {
            return newstime;
        }

        public void setNewstime(String newstime) {
            this.newstime = newstime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public long getUpdater() {
            return updater;
        }

        public void setUpdater(long updater) {
            this.updater = updater;
        }

        public long getCreator() {
            return creator;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }

        public String getNewsTypeName() {
            return newsTypeName;
        }

        public void setNewsTypeName(String newsTypeName) {
            this.newsTypeName = newsTypeName;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }
    }

}
