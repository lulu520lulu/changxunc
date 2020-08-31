package com.c.hangxunc.bean.home;


public class AdvertisingsBean {
    /**
     * 0 : {"title":"每日好店","link":"index.php?route=product/manufacturer/info&manufacturer_id=12","image":"http://c.hangxunc.com/image/catalog/app/advertised/Fendi.jpg"}
     * 1 : {"title":"发现更多人气好货","link":"index.php?route=product/manufacturer/info&manufacturer_id=13","image":"http://c.hangxunc.com/image/catalog/app/advertised/乔丹.jpg"}
     * title : 每日好店
     * subtitle : 发现更多人气好货
     */

    private String title;
    private String subtitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public static class _$0BeanX {
        /**
         * title : 每日好店
         * link : index.php?route=product/manufacturer/info&manufacturer_id=12
         * image : http://c.hangxunc.com/image/catalog/app/advertised/Fendi.jpg
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
    }

    public static class _$1BeanX {
        /**
         * title : 发现更多人气好货
         * link : index.php?route=product/manufacturer/info&manufacturer_id=13
         * image : http://c.hangxunc.com/image/catalog/app/advertised/乔丹.jpg
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
    }
}
