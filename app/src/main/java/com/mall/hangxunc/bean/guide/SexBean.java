package com.mall.hangxunc.bean.guide;

import com.google.gson.annotations.SerializedName;

public class SexBean {
    /**
     * man : {"man":"男士","image":"http://c.hangxunc.com/image/catalog/app/user-style/man-img.png"}
     * woman : {"woman":"女士","image":"http://c.hangxunc.com/image/catalog/app/user-style/woman.png"}
     */

    private ManBean man;
    private WomanBean woman;

    public ManBean getMan() {
        return man;
    }

    public void setMan(ManBean man) {
        this.man = man;
    }

    public WomanBean getWoman() {
        return woman;
    }

    public void setWoman(WomanBean woman) {
        this.woman = woman;
    }

    public static class ManBean {

        /**
         * woman : 女士
         * image : http://c.hangxunc.com/image/catalog/app/user-style/woman-img.png
         * image-checked : http://c.hangxunc.com/image/catalog/app/user-style/woman-img-checked.png
         */

        private String man;
        private String image;
        @SerializedName("image-checked")
        private String imagechecked;

        public String getMan() {
            return man;
        }

        public void setMan(String man) {
            this.man = man;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImagechecked() {
            return imagechecked;
        }

        public void setImagechecked(String imagechecked) {
            this.imagechecked = imagechecked;
        }
    }

    public static class WomanBean {

        /**
         * woman : 女士
         * image : http://c.hangxunc.com/image/catalog/app/user-style/woman-img.png
         * image-checked : http://c.hangxunc.com/image/catalog/app/user-style/woman-img-checked.png
         */

        private String woman;
        private String image;
        @SerializedName("image-checked")
        private String imagechecked;

        public String getWoman() {
            return woman;
        }

        public void setWoman(String woman) {
            this.woman = woman;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImagechecked() {
            return imagechecked;
        }

        public void setImagechecked(String imagechecked) {
            this.imagechecked = imagechecked;
        }
    }
}
