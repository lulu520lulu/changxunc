package com.mall.hangxunc.pages.center.center;

import java.util.List;

public class XqkChildBean {
    /**
     * total : 14
     * list : [{"id":1286974713844375554,"uuid":null,"title":"qwe","intro":"qweq","detail":"qweqe","industryId":1268723095199854594,"typeCode":"xq_first","status":3,"creator":1286971830562394114,"createDate":1595673657000,"updater":1286971830562394114,"updateDate":1595673657000,"countryId":110101,"remark":"123123","budget":122,"deadline":"2020-07-31","demander":"1213","creator_tel":null,"creator_email":null,"industryName":"需求库1","creatorName":null,"typeName":"需求1","parentCountryName":"北京市","parentCountryId":110100,"statusStr":"待解决","tempId":"1286974713844375554","tempCreatorId":null,"languageCode":"zh-CN","countryName":"东城区"},{"id":1278600120240140290,"uuid":null,"title":"测试名称","intro":"测试简介","detail":"测试详情","industryId":1268723258651881474,"typeCode":"xq_secend","status":3,"creator":1265916853003816961,"createDate":1593676998000,"updater":1067246875800000001,"updateDate":1595605419000,"countryId":370202,"remark":"测试备注","budget":300000,"deadline":"2020-07-08","demander":"测试联系人","creator_tel":null,"creator_email":null,"industryName":"需求库2","creatorName":null,"typeName":"需求2","parentCountryName":"青岛市","parentCountryId":370200,"statusStr":"待解决","tempId":"1278600120240140290","tempCreatorId":null,"languageCode":"zh-CN","countryName":"市南区"},{"id":1278886122800168962,"uuid":null,"title":"发布测试","intro":"发布测试","detail":"发布测试","industryId":1268723095199854594,"typeCode":"xq_first","status":3,"creator":1265916853003816961,"createDate":1593745186000,"updater":1067246875800000001,"updateDate":1595605397000,"countryId":10000000000,"remark":"发布测试","budget":159753,"deadline":"2022-07-03","demander":"发布测试","creator_tel":null,"creator_email":null,"industryName":"需求库1","creatorName":null,"typeName":"需求1","parentCountryName":"","parentCountryId":0,"statusStr":"待解决","tempId":"1278886122800168962","tempCreatorId":null,"languageCode":"zh-CN","countryName":"乌兹别克斯坦"},{"id":1286686825688813569,"uuid":"","title":"sgwesdfsdf","intro":"sfdgsdg","detail":"sdger","industryId":1251121525781540865,"typeCode":"xq_first","status":3,"creator":1067246875800000001,"createDate":1595605019000,"updater":1067246875800000001,"updateDate":1595605039000,"countryId":10000000,"remark":"ert34","budget":23423,"deadline":"2020-07-29","demander":"234234","creator_tel":null,"creator_email":null,"industryName":"冷库街口","creatorName":null,"typeName":"需求1","parentCountryName":"","parentCountryId":0,"statusStr":"待解决","tempId":"1286686825688813569","tempCreatorId":null,"languageCode":"zh-CN","countryName":"哈萨克斯坦"}]
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
         * id : 1286974713844375554
         * uuid : null
         * title : qwe
         * intro : qweq
         * detail : qweqe
         * industryId : 1268723095199854594
         * typeCode : xq_first
         * status : 3
         * creator : 1286971830562394114
         * createDate : 1595673657000
         * updater : 1286971830562394114
         * updateDate : 1595673657000
         * countryId : 110101
         * remark : 123123
         * budget : 122.0
         * deadline : 2020-07-31
         * demander : 1213
         * creator_tel : null
         * creator_email : null
         * industryName : 需求库1
         * creatorName : null
         * typeName : 需求1
         * parentCountryName : 北京市
         * parentCountryId : 110100
         * statusStr : 待解决
         * tempId : 1286974713844375554
         * tempCreatorId : null
         * languageCode : zh-CN
         * countryName : 东城区
         */

        private long id;
        private Object uuid;
        private String title;
        private String intro;
        private String detail;
        private long industryId;
        private String typeCode;
        private int status;
        private long creator;
        private long createDate;
        private long updater;
        private long updateDate;
        private String countryId;
        private String remark;
        private double budget;
        private String deadline;
        private String demander;
        private Object creator_tel;
        private Object creator_email;
        private String industryName;
        private Object creatorName;
        private String typeName;
        private String parentCountryName;
        private int parentCountryId;
        private String statusStr;
        private String tempId;
        private Object tempCreatorId;
        private String languageCode;
        private String countryName;
        private String detailsLink;

        public String getDetailsLink() {
            return detailsLink;
        }

        public void setDetailsLink(String detailsLink) {
            this.detailsLink = detailsLink;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getUuid() {
            return uuid;
        }

        public void setUuid(Object uuid) {
            this.uuid = uuid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getIndustryId() {
            return industryId;
        }

        public void setIndustryId(long industryId) {
            this.industryId = industryId;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreator() {
            return creator;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
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

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getBudget() {
            return budget;
        }

        public void setBudget(double budget) {
            this.budget = budget;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getDemander() {
            return demander;
        }

        public void setDemander(String demander) {
            this.demander = demander;
        }

        public Object getCreator_tel() {
            return creator_tel;
        }

        public void setCreator_tel(Object creator_tel) {
            this.creator_tel = creator_tel;
        }

        public Object getCreator_email() {
            return creator_email;
        }

        public void setCreator_email(Object creator_email) {
            this.creator_email = creator_email;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public Object getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(Object creatorName) {
            this.creatorName = creatorName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getParentCountryName() {
            return parentCountryName;
        }

        public void setParentCountryName(String parentCountryName) {
            this.parentCountryName = parentCountryName;
        }

        public int getParentCountryId() {
            return parentCountryId;
        }

        public void setParentCountryId(int parentCountryId) {
            this.parentCountryId = parentCountryId;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public String getTempId() {
            return tempId;
        }

        public void setTempId(String tempId) {
            this.tempId = tempId;
        }

        public Object getTempCreatorId() {
            return tempCreatorId;
        }

        public void setTempCreatorId(Object tempCreatorId) {
            this.tempCreatorId = tempCreatorId;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }
}

