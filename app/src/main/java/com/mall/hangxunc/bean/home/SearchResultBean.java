package com.mall.hangxunc.bean.home;

import java.util.List;

public class SearchResultBean {

    private String heading_title;
    private String pagination;
    private String results;
    private String style;
    private String style_grid;
    private String style_list;
    private String search;
    private String description;
    private String sub_category;
    private String sort;
    private String order;
    private String limit;
    private List<ProductBean> products;
    private List<SortsBean> sorts;
    private List<LimitsBean> limits;

    public String getHeading_title() {
        return heading_title;
    }

    public void setHeading_title(String heading_title) {
        this.heading_title = heading_title;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle_grid() {
        return style_grid;
    }

    public void setStyle_grid(String style_grid) {
        this.style_grid = style_grid;
    }

    public String getStyle_list() {
        return style_list;
    }

    public void setStyle_list(String style_list) {
        this.style_list = style_list;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    public List<SortsBean> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortsBean> sorts) {
        this.sorts = sorts;
    }

    public List<LimitsBean> getLimits() {
        return limits;
    }

    public void setLimits(List<LimitsBean> limits) {
        this.limits = limits;
    }

}
