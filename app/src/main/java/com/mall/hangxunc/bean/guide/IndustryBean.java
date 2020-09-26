package com.mall.hangxunc.bean.guide;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class IndustryBean implements Serializable {

    private boolean isSelect;
    private String id;
    private long pid;
    private String name;
    @SerializedName("code")
    private String codeX;
    private int level;
    private String keywords;
    private int orderNum;
    private String remark;
    private Object updater;
    private Object updateDate;
    private Object parentName;
    private String pids;
    private Object xqkEntityList;
    private Object techAchievementEntityList;
    private String languageCode;
    private String imgPath;
    private List<?> industryList;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeX() {
        return codeX;
    }

    public void setCodeX(String codeX) {
        this.codeX = codeX;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getUpdater() {
        return updater;
    }

    public void setUpdater(Object updater) {
        this.updater = updater;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public Object getParentName() {
        return parentName;
    }

    public void setParentName(Object parentName) {
        this.parentName = parentName;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public Object getXqkEntityList() {
        return xqkEntityList;
    }

    public void setXqkEntityList(Object xqkEntityList) {
        this.xqkEntityList = xqkEntityList;
    }

    public Object getTechAchievementEntityList() {
        return techAchievementEntityList;
    }

    public void setTechAchievementEntityList(Object techAchievementEntityList) {
        this.techAchievementEntityList = techAchievementEntityList;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<?> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<?> industryList) {
        this.industryList = industryList;
    }
}
