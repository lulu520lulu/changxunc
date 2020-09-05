package com.c.hangxunc.bean.guide;

public class InterestBean {
    /**
     * name : 健康
     * image : http://c.hangxunc.com/image/catalog/app/user-style/jk.png
     */

    private String name;
    boolean isSelect;
    private String image;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
