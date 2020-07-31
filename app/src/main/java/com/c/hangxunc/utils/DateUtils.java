package com.c.hangxunc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String strToDate(String string) {
        // 将date字符串转化为日期
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            return date.toString();
        }
        return "";
    }
}
