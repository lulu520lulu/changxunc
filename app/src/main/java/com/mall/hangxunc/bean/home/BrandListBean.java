package com.mall.hangxunc.bean.home;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BrandListBean implements Serializable {
    /**
     * breadcrumbs : [{"text":"<i class=\"fa fa-home\"><\/i>","href":"http://c.hangxunc.com/"},{"text":"品牌","href":"http://c.hangxunc.com/index.php?route=product/manufacturer"}]
     * categories : {"H":{"name":"H","manufacturer":[{"name":"HAPPYSPACE 欢乐空间","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=12"}]},"K":{"name":"K","manufacturer":[{"name":"KEENZ","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=13"}]}}
     * continue : http://c.hangxunc.com/
     * href : index.php?route=api/ioslink/getCategory
     */

    private CategoriesBean categories;
    @SerializedName("continue")
    private String continueX;
    private String href;
    private List<BreadcrumbsBean> breadcrumbs;

    public CategoriesBean getCategories() {
        return categories;
    }

    public void setCategories(CategoriesBean categories) {
        this.categories = categories;
    }

    public String getContinueX() {
        return continueX;
    }

    public void setContinueX(String continueX) {
        this.continueX = continueX;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<BreadcrumbsBean> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(List<BreadcrumbsBean> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public static class CategoriesBean {
        /**
         * H : {"name":"H","manufacturer":[{"name":"HAPPYSPACE 欢乐空间","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=12"}]}
         * K : {"name":"K","manufacturer":[{"name":"KEENZ","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=13"}]}
         */

        private HBean H;
        private HBean K;

        public HBean getH() {
            return H;
        }

        public void setH(HBean H) {
            this.H = H;
        }

        public HBean getK() {
            return K;
        }

        public void setK(HBean K) {
            this.K = K;
        }

        public static class HBean {
            /**
             * name : H
             * manufacturer : [{"name":"HAPPYSPACE 欢乐空间","thumb":"http://c.hangxunc.com/image/cache/catalog/app/占位图-200x200.jpg","href":"http://c.hangxunc.com/index.php?route=product/manufacturer/info&manufacturer_id=12"}]
             */

            private String name;
            private List<TypeChildrenBean> manufacturer;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<TypeChildrenBean> getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(List<TypeChildrenBean> manufacturer) {
                this.manufacturer = manufacturer;
            }

        }

    }

    public static class BreadcrumbsBean {
        /**
         * text : <i class="fa fa-home"></i>
         * href : http://c.hangxunc.com/
         */

        private String text;
        private String href;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

}
