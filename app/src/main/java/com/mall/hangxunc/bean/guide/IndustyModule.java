package com.mall.hangxunc.bean.guide;

import com.mall.hangxunc.bean.home.BaseBean;

public class IndustyModule extends BaseBean {

    /**
     * data : {"industry":[{"id":"1306783787222462465","pid":1306783272262594600,"name":"电子信息","code":"DZXX","level":0,"keywords":"电子信息","orderNum":0,"remark":"","updater":null,"updateDate":null,"parentName":null,"pids":"1306783272262594562","xqkEntityList":null,"techAchievementEntityList":null,"languageCode":"zh-CN","imgPath":"http://d.hangxunc.com:8081/scocenter/static/index/industry/教育培训.png","industryList":[]},{"id":"1306785049250787330","pid":1306783272262594600,"name":"农、林、牧、渔业","code":"农、林、牧、渔业","level":0,"keywords":"农、林、牧、渔业","orderNum":0,"remark":"农、林、牧、渔业","updater":null,"updateDate":null,"parentName":null,"pids":"1306783272262594562","xqkEntityList":null,"techAchievementEntityList":null,"languageCode":"zh-CN","imgPath":"http://d.hangxunc.com:8081/scocenter/static/index/industry/教育培训.png","industryList":[{"id":"1306785325613477889","pid":1306785049250787300,"name":"农业","code":"农业","level":0,"keywords":"农业","orderNum":0,"remark":"","updater":null,"updateDate":null,"parentName":null,"pids":"1306783272262594562,1306785049250787330","xqkEntityList":null,"techAchievementEntityList":null,"languageCode":"zh-CN","imgPath":null,"industryList":[{"id":"1306787227524820994","pid":1306785325613478000,"name":"谷物及其他作物的种植","code":"谷物及其他作物的种植","level":0,"keywords":"谷物及其他作物的种植","orderNum":0,"remark":"","updater":null,"updateDate":null,"parentName":null,"pids":"1306783272262594562,1306785049250787330,1306785325613477889","xqkEntityList":null,"techAchievementEntityList":null,"languageCode":"zh-CN","imgPath":null,"industryList":null},{"id":"1306795181724561409","pid":1306785325613478000,"name":"蔬菜、园艺作物的种植","code":"蔬菜、园艺作物的种植","level":0,"keywords":"蔬菜、园艺作物的种植","orderNum":0,"remark":"","updater":null,"updateDate":null,"parentName":null,"pids":"1306783272262594562,1306785049250787330,1306785325613477889","xqkEntityList":null,"techAchievementEntityList":null,"languageCode":"zh-CN","imgPath":null,"industryList":null}]}]}]}
     */

    private IndustyData data;

    public IndustyData getData() {
        return data;
    }

    public void setData(IndustyData data) {
        this.data = data;
    }

}
