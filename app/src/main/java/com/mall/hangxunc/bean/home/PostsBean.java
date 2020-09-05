package com.mall.hangxunc.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

public class PostsBean implements Parcelable {
    /**
     * post_id : 16
     * status : 1
     * viewed : 20
     * sort_order : 0
     * date_added : 2020-05-20 14:20:42
     * date_modified : 2020-07-19 12:34:11
     * description_id : 308
     * language_id : 2
     * name : 2020上海国际橡塑展（4）
     * image : catalog/trade_show_banner.jpg
     * description :
     * content : &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;2020上海国际橡塑展&lt;/p&gt;
     * &lt;p&gt;&amp;nbsp;&lt;/p&gt;
     * meta_title :
     * meta_description :
     * meta_keyword :
     * author :
     * category_id : 4
     */

    private int post_id;
    private int status;
    private int viewed;
    private int sort_order;
    private String date_added;
    private String date_modified;
    private int description_id;
    private int language_id;
    private String name;
    private String image;
    private String description;
    private String content;
    private String meta_title;
    private String meta_description;
    private String meta_keyword;
    private String author;
    private int category_id;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
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

    public int getDescription_id() {
        return description_id;
    }

    public void setDescription_id(int description_id) {
        this.description_id = description_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.post_id);
        dest.writeInt(this.status);
        dest.writeInt(this.viewed);
        dest.writeInt(this.sort_order);
        dest.writeString(this.date_added);
        dest.writeString(this.date_modified);
        dest.writeInt(this.description_id);
        dest.writeInt(this.language_id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeString(this.description);
        dest.writeString(this.content);
        dest.writeString(this.meta_title);
        dest.writeString(this.meta_description);
        dest.writeString(this.meta_keyword);
        dest.writeString(this.author);
        dest.writeInt(this.category_id);
    }

    public PostsBean() {
    }

    protected PostsBean(Parcel in) {
        this.post_id = in.readInt();
        this.status = in.readInt();
        this.viewed = in.readInt();
        this.sort_order = in.readInt();
        this.date_added = in.readString();
        this.date_modified = in.readString();
        this.description_id = in.readInt();
        this.language_id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
        this.description = in.readString();
        this.content = in.readString();
        this.meta_title = in.readString();
        this.meta_description = in.readString();
        this.meta_keyword = in.readString();
        this.author = in.readString();
        this.category_id = in.readInt();
    }

    public static final Creator<PostsBean> CREATOR = new Creator<PostsBean>() {
        @Override
        public PostsBean createFromParcel(Parcel source) {
            return new PostsBean(source);
        }

        @Override
        public PostsBean[] newArray(int size) {
            return new PostsBean[size];
        }
    };
}
