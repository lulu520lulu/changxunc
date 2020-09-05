package com.mall.hangxunc.bean.home;

import java.util.List;

public class CategoryChildBean {
    /**
     * name : 制造与加工机械
     * thumb : http://b.hangxunc.com/image/cache/catalog/demo/banner/9-80x80.jpg
     * children : [{"thumb":"http://b.hangxunc.com/image/cache/catalog/nongji-80x80.png","name":"农业机械","href":"http://b.hangxunc.com/index.php?route=product/category&amp;path=59_114","grand_children":[]},{"thumb":"http://b.hangxunc.com/image/cache/catalog/suliao-80x80.png","name":"塑料和木工机械","href":"http://b.hangxunc.com/index.php?route=product/category&amp;path=59_119","grand_children":[]},{"thumb":"http://b.hangxunc.com/image/cache/catalog/pipemachine-80x80.png","name":"工程机械","href":"http://b.hangxunc.com/index.php?route=product/category&amp;path=59_121","grand_children":[]},{"thumb":"http://b.hangxunc.com/image/cache/catalog/gongshe1-80x80.png","name":"机械工具","href":"http://b.hangxunc.com/index.php?route=product/category&amp;path=59_116","grand_children":[]},{"thumb":"http://b.hangxunc.com/image/cache/catalog/other2-80x80.png","name":"其他机械零件","href":"http://b.hangxunc.com/index.php?route=product/category&amp;path=59_123","grand_children":[]}]
     * href : http://b.hangxunc.com/index.php?route=product/category&amp;path=59
     */

    private String name;
    private String thumb;
    private String href;
    private List<ChildrenBean> children;
    private String category_id;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
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
}

