package com.c.hangxunc.bean.home;

import java.util.List;

public class LanguageListBean {

    /**
     * code : zh-cn
     * languages : [{"name":"English","code":"en-gb","href":"http://b.hangxunc.com/index.php?route=api/ioslink/getLanguage&amp;language=en-gb"},{"name":"简体中文","code":"zh-cn","href":"http://b.hangxunc.com/index.php?route=api/ioslink/getLanguage&amp;language=zh-cn"},{"name":"Russian","code":"ru-ru","href":"http://b.hangxunc.com/index.php?route=api/ioslink/getLanguage&amp;language=ru-ru"}]
     */

    private String code;
    private List<LanguageBean> languages;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LanguageBean> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageBean> languages) {
        this.languages = languages;
    }

}
