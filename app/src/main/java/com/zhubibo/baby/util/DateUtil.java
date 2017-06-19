package com.zhubibo.baby.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 2017/6/16.
 */

public class DateUtil {


    /**
     * 获取字符串的日期
     * @param dateStr    指定日期
     * @param dateFormat 指定日期的格式
     * @return
     */
    public static Date getDate(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            Log.e("DateUtil", "转换日期异常：" + e);
            return new Date();
        }
    }

    /**
     * 获取指定格式的日期字符串
     * @param date       指定日期
     * @param dateFormat 指定日期的格式
     * @return
     */
    public static String getDateStr(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 计算当前日期和inputDate的相差天数
     * @param inputDate    指定日期
     * @return
     */
    public static int calcDiffDay(Date inputDate) {

        long inputTimeLong = inputDate.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDateStr = sdf.format(new Date());
        Date nowDate = getDate(nowDateStr, "yyyy-MM-dd");
        long nowTimeLong = nowDate.getTime();

        return (int) ((inputTimeLong - nowTimeLong) / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算今天是否是生日
     * @param inputDate    指定日期
     * @return
     */
    public static boolean isBirthday(Date inputDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int inputDay = calendar.get(Calendar.DAY_OF_MONTH);
        int inputMonth = calendar.get(Calendar.MONTH) + 1;

        calendar.setTime(new Date());
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;

        return inputMonth == nowMonth && inputDay == nowDay;
    }

    /**
     * 获取生日岁数
     * @param inputDate    指定日期
     * @return
     */
    public static int getBirthday(Date inputDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int inputYear = calendar.get(Calendar.YEAR);

        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);

        return Math.abs(nowYear - inputYear);
    }
}
