package com.sp.xwjlibrary.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vinson on 2017/7/20.
 */

public class TimeUtil {

    /**
     * 时间显示
     * @param sumTime,单位s
     * @return
     */
    public static String showTimeMode(long sumTime) {

        long day = sumTime / (3600 * 24);
        long hour = sumTime % (3600 * 24) / 3600;
        long minute = sumTime % (3600 * 24) % 3600 / 60;
        long second = sumTime % (3600 * 24) % 3600 % 60;

        StringBuffer sb = new StringBuffer();
        sb.append(day + "天");
        if(hour < 10){
            sb.append("0");
        }
        sb.append(hour + ":");
        if(minute < 10){
            sb.append("0");
        }
        sb.append(minute + ":");
        if(second < 10){
            sb.append("0");
        }
        sb.append(second);
        return sb.toString();
    }

    /**
     * 时间字符串格式转换
     * @param src 时间字符串
     * @param pattern src时间字符串的格式
     * @param parse 转换后的格式
     * @return 转换后的时间字符串
     */
    public static String parseDate(String src, String pattern,String parse){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String string = "";
        try {
            Date date = sdf.parse(src);
            SimpleDateFormat format = new SimpleDateFormat(parse);
            string = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 获取当前时间
     * @param pattern 时间显示格式
     * @return
     */
    public static String getCurrTime(String pattern){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 获取时间,通过时间长度
     * @param time
     * @param pattern
     * @return
     */
    public static String getTimeByTimeLen(long time,String pattern){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 获取时间长度,通过时间字符串
     * @param time
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static long getTimeLenByTimeStr(String time, String pattern) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(time);
            if (date == null) {
                return 0;
            } else {
                return date.getTime();
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 提取日期中的年/月/日/时/分/秒
     * @param time
     * @param pattern
     * @param calendarType Calender类中
     * @return
     */
    public static int extractDate(String time, String pattern, int calendarType){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int c = calendar.get(calendarType);
            if (calendarType == Calendar.MONTH)
                return c + 1;
            else
                return c;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置时间
     * @param time 时间
     * @param inPattern 时间输入格式
     * @param outPattern 时间输出格式
     * @param dateAdd 日期增量
     * @param hourAdd 小时增量
     * @param minAdd 分钟增量
     * @param secAdd 秒钟增量
     * @return
     */
    public static String setTime(String time,String inPattern,String outPattern,
                                 int dateAdd,int hourAdd,int minAdd,int secAdd){
        try {
            Date date = new SimpleDateFormat(inPattern).parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,dateAdd);
            calendar.add(Calendar.HOUR,hourAdd);
            calendar.add(Calendar.MINUTE,minAdd);
            calendar.add(Calendar.SECOND,secAdd);
            date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(outPattern);
            String format = sdf.format(date);
            return format;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 设置日期
     * @param time 时间
     * @param inPattern 时间输入格式
     * @param outPattern 时间输出格式
     * @param yearAdd 年增量
     * @param monthAdd 月增量
     * @param dayAdd 日增量
     * @param isOneDay 是否初始化到1号,当dayAdd=0时生效,true表示一号,false表示上个月最后一天
     * @return
     */
    public static String setDate(String time,String inPattern,String outPattern,
                                 int yearAdd,int monthAdd,int dayAdd,boolean isOneDay){
        try {
            Date date = new SimpleDateFormat(inPattern).parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,yearAdd);
            calendar.add(Calendar.MONTH,monthAdd);
            if (dayAdd == 0){
                if (isOneDay) {
                    calendar.add(Calendar.DAY_OF_MONTH,- calendar.get(Calendar.DAY_OF_MONTH) + 1);
                }else {
                    calendar.add(Calendar.DAY_OF_MONTH,- calendar.get(Calendar.DAY_OF_MONTH));
                }
            }else{
                calendar.add(Calendar.DAY_OF_MONTH,dayAdd);
            }
            date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(outPattern);
            String format = sdf.format(date);
            return format;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当月天数
     * */
    public static int getCurrentMonthOfDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.get(Calendar.DATE);
    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthOfDay(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当月第一天是星期几,从星期天到星期六为1~7
     * @return
     */
    public static int getCurrentMonthFirstDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当天是几号
     * @return
     */
    public static int getDayOfMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取两个日期段之间的日期
     * @param pattern
     * @param startDate
     * @param endDate
     * @return
     */
    public static HashMap<String,List<Integer>> getDayOfBetweenDate(
            String pattern, String startDate, String endDate){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        HashMap<String,List<Integer>> yearMonthMap = new HashMap<>();
        try {
            Date sDate = sdf.parse(startDate);
            Date eDate = sdf.parse(endDate);
            Calendar sCalendar = Calendar.getInstance();
            Calendar eCalendar = Calendar.getInstance();
            sCalendar.setTime(sDate);
            eCalendar.setTime(eDate);

            int lastMonth = -1;
            List<Integer> dayOfMonths = new ArrayList<>();
            while (sCalendar.compareTo(eCalendar) == -1 || sCalendar.compareTo(eCalendar) == 0){
                int sMonth = sCalendar.get(Calendar.MONTH);
                if (lastMonth != sMonth){
                    dayOfMonths = new ArrayList<>();
                }
                dayOfMonths.add(sCalendar.get(Calendar.DAY_OF_MONTH));
                int month = sCalendar.get(Calendar.MONTH) + 1;
                StringBuffer monthSB = new StringBuffer();
                if (month < 10) {
                    monthSB.append("0");
                }
                monthSB.append(month);
                String yearMonth = sCalendar.get(Calendar.YEAR) + "-" + monthSB.toString();
                yearMonthMap.put(yearMonth,dayOfMonths);
                lastMonth = sCalendar.get(Calendar.MONTH);
                sCalendar.add(Calendar.DAY_OF_MONTH,+1);
            }

            return yearMonthMap;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return yearMonthMap;
    }

    /**
     * 判断两个日期是否同一个月,月份一样,年份不一样视为不一样
     * @param pattern
     * @param startDate
     * @param endDate
     */
    public static boolean isSameMonthOfBothDate(String pattern, String startDate, String endDate){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date sDate = sdf.parse(startDate);
            Date eDate = sdf.parse(endDate);
            Calendar sCalendar = Calendar.getInstance();
            Calendar eCalendar = Calendar.getInstance();
            sCalendar.setTime(sDate);
            eCalendar.setTime(eDate);
            if (sCalendar.get(Calendar.YEAR) != eCalendar.get(Calendar.YEAR)){
                return false;
            }else{
                if (sCalendar.get(Calendar.MONTH) != eCalendar.get(Calendar.MONTH)){
                    return false;
                }else{
                    return true;
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断某个时间是否在区间内,闭区间(格式:yyyy-MM-dd)
     * @param time 需要判断的时间
     * @param leftTime 左区间
     * @param rightTime 右区间
     * @return
     */
    public static boolean isInInterval(String time,String leftTime,String rightTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date t = sdf.parse(time);
            Date t1 = sdf.parse(leftTime);
            Date t2 = sdf.parse(rightTime);
            if (t.compareTo(t1) < 0){
                return false;
            }
            if (t.compareTo(t2) > 0){
                return false;
            }
            if (t.compareTo(t1) >= 0 && t.compareTo(t2) <= 0){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 比较两个时间(格式:yyyy-MM-dd)
     * @param time1
     * @param time2
     * @return
     * 		an int < 0 if this Date is less than the specified Date,
     * 		0 if they are equal, and an int > 0 if this Date is greater.
     */
    public static int compare2Time(String format,String time1,String time2){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date t1 = sdf.parse(time1);
            Date t2 = sdf.parse(time2);
            return t1.compareTo(t2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 比较两个时间(格式:yyyy-MM)
     * @param time1
     * @param time2
     * @return
     * 		an int < 0 if this Date is less than the specified Date,
     * 		0 if they are equal, and an int > 0 if this Date is greater.
     */
    public static int compare2YearMonth(String time1,String time2){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date t1 = sdf.parse(time1);
            Date t2 = sdf.parse(time2);
            return t1.compareTo(t2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 比较两个时间(年为4位数,月与日为2位数,年月日之前用一个字符隔开)
     * @param date1
     * @param date2
     * @return
     * 		an int < 0 if this Date is less than the specified Date,
     * 		0 if they are equal, and an int > 0 if this Date is greater.
     */
    public static int compare2Date(String date1,String date2){
        try {
            String yearStr1 = date1.substring(0, 4);
            String monthStr1 = date1.substring(5, 7);
            String dayStr1 = date1.substring(8, 10);
            String hourStr1 = date1.substring(11, 13);
            String minStr1 = date1.substring(14, 16);
            String secStr1 = date1.substring(17, 19);

            int year1 = Integer.valueOf(yearStr1);
            int month1 = Integer.valueOf(monthStr1);
            int day1 = Integer.valueOf(dayStr1);
            int hour1 = Integer.valueOf(hourStr1);
            int min1 = Integer.valueOf(minStr1);
            int sec1 = Integer.valueOf(secStr1);

            String yearStr2 = date2.substring(0, 4);
            String monthStr2 = date2.substring(5, 7);
            String dayStr2 = date2.substring(8, 10);
            String hourStr2 = date2.substring(11, 13);
            String minStr2 = date2.substring(14, 16);
            String secStr2 = date2.substring(17, 19);

            int year2 = Integer.valueOf(yearStr2);
            int month2 = Integer.valueOf(monthStr2);
            int day2 = Integer.valueOf(dayStr2);
            int hour2 = Integer.valueOf(hourStr2);
            int min2 = Integer.valueOf(minStr2);
            int sec2 = Integer.valueOf(secStr2);

            if(year1 < year2){
                return -1;
            }
            if(year1 > year2){
                return 1;
            }
            if(year1 == year2){
                if(month1 < month2){
                    return -1;
                }
                if(month1 > month2){
                    return 1;
                }
                if(month1 == month2){
                    if(day1 < day2){
                        return -1;
                    }
                    if(day1 > day2){
                        return 1;
                    }
                    if(day1 == day2){
                        if (hour1 < hour2){
                            return -1;
                        }
                        if(hour1 > hour2){
                            return 1;
                        }
                        if(hour1 == hour2){
                            if (min1 < min2){
                                return -1;
                            }
                            if(min1 > min2){
                                return 1;
                            }
                            if(min1 == min2){
                                if (sec1 < sec2){
                                    return -1;
                                }
                                if(sec1 > sec2){
                                    return 1;
                                }
                                if(sec1 == sec2){
                                    return 0;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("自定义工具库","时间格式错误");
        }
        return 0;
    }

}








