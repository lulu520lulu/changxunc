package com.mall.hangxunc.bean.home;

import java.util.List;

public class TypeChildrenBean {
    /**
     * thumb : http://b.hangxunc.com/image/cache/catalog/nongji-80x80.png
     * name : 农业机械
     * href : http://b.hangxunc.com/index.php?route=product/category&amp;path=59_114
     * grand_children : []
     */

    private String thumb;
    private String name;
    private String href;
    private List<?> grand_children;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<?> getGrand_children() {
        return grand_children;
    }

    public void setGrand_children(List<?> grand_children) {
        this.grand_children = grand_children;
    }

}
