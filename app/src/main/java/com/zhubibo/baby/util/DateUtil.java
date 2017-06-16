package com.zhubibo.baby.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kevin on 2017/6/16.
 */

public class DateUtil {

    /**
     * 计算当前日期和dueDateStr的相差天数
     * @param dateStr    指定日期
     * @param dateFormat 指定日期的格式
     * @return
     */
    public static int calcDiffDay(String dateStr, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date inputDate = new Date();

        try {
            inputDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        long inputTimeLong = calendar.getTimeInMillis();

        String nowDateStr = sdf.format(new Date());
        Date nowDate = new Date();
        try {
            nowDate = sdf.parse(nowDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(nowDate);
        long nowTimeLong = calendar.getTimeInMillis();

        return (int) ((inputTimeLong - nowTimeLong) / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算今天是否是生日
     * @param dateStr    指定日期
     * @param dateFormat 指定日期的格式
     * @return
     */
    public static boolean isBirthday(String dateStr, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date inputDate = new Date();

        try {
            inputDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
     * @param dateStr    指定日期
     * @param dateFormat 指定日期的格式
     * @return
     */
    public static int getBirthday(String dateStr, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date inputDate = new Date();

        try {
            inputDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int inputYear = calendar.get(Calendar.YEAR);

        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);

        return Math.abs(nowYear - inputYear);
    }
}
