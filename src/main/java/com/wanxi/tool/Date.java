package com.wanxi.tool;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;


@Builder
@NoArgsConstructor
public class Date {
    public static String getDate() {
        java.util.Date date = new java.util.Date();
//        日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = dateFormat.format(date);
        return format;
    }
}
