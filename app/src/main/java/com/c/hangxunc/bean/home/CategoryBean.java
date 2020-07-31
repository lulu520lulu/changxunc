package com.c.hangxunc.bean.home;

public class CategoryBean {

    /**
     * category_id : 59
     * image : catalog/demo/banner/9.jpg
     * parent_id : 0
     * top : 1
     * column : 1
     * sort_order : 1
     * status : 1
     * twig :
     * date_added : 2018-04-27 10:11:23
     * date_modified : 2020-04-28 09:53:15
     * language_id : 2
     * name : 制造与加工机械
     * description :
     * meta_title : 制造与加工机械
     * meta_description :
     * meta_keyword :
     * store_id : 0
     * category_href : index.php?route=product/category&path=59
     */

    private String category_id;
    private String image;
    private String parent_id;
    private String top;
    private String column;
    private String sort_order;
    private String status;
    private String twig;
    private String date_added;
    private String date_modified;
    private String language_id;
    private String name;
    private String description;
    private String meta_title;
    private String meta_description;
    private String meta_keyword;
    private String store_id;
    private String category_href;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTwig() {
        return twig;
    }

    public void setTwig(String twig) {
        this.twig = twig;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCategory_href() {
        return category_href;
    }

    public void setCategory_href(String category_href) {
        this.category_href = category_href;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "category_id='" + category_id + '\'' +
                ", image='" + image + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", top='" + top + '\'' +
                ", column='" + column + '\'' +
                ", sort_order='" + sort_order + '\'' +
                ", status='" + status + '\'' +
                ", twig='" + twig + '\'' +
                ", date_added='" + date_added + '\'' +
                ", date_modified='" + date_modified + '\'' +
                ", language_id='" + language_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", meta_title='" + meta_title + '\'' +
                ", meta_description='" + meta_description + '\'' +
                ", meta_keyword='" + meta_keyword + '\'' +
                ", store_id='" + store_id + '\'' +
                ", category_href='" + category_href + '\'' +
                '}';
    }
}
