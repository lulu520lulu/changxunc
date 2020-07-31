package com.c.hangxunc.bean.home;

public class BannersBean {

    /**
     * title : sss
     * link : index.php?route=product/product&product_id=48
     * image : http://c.hangxunc.com/image/catalog/app/banner/app-banner.jpg
     */

    private String title;
    private String link;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BannersBean{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
