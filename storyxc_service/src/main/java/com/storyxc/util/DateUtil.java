package com.storyxc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/24 18:45
 */
public class DateUtil {


    public static String parseDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }
}
