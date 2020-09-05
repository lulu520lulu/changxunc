package com.mall.hangxunc.bean.home;

public class ItemsBean {

    /**
     * image : http://c.hangxunc.com/image/catalog/app/icon/科技创意.png
     * title : 科技成果
     * href : index.php?route=product/tec_creativity_page
     */

    private String image;
    private String title;
    private String href;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "ItemsBean{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
