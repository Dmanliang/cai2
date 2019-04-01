package com.sp.util;

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/5/17.
 */

public class TimeUtils {

    private static SimpleDateFormat sf = null;

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getNowTime() {
        String timeString = null;
        Time time = new Time();
        time.setToNow();
        String year = thanTen(time.year);
        String month = thanTen(time.month + 1);
        String monthDay = thanTen(time.monthDay);
        String hour = thanTen(time.hour);
        String minute = thanTen(time.minute);

        timeString = year + "-" + month + "-" + monthDay + " " + hour + ":"
                + minute;
        return timeString;
    }

    public static final String getNowDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        return date;
    }

    public int calculate(int year, int month) {

        boolean yearleap = judge(year);
        int day;
        if (yearleap && month == 2) {
            day = 29;
        } else if (!yearleap && month == 2) {
            day = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        } else {
            day = 31;
        }
        return day;
    }

    public boolean judge(int year) {
        boolean yearleap = (year % 400 == 0) || (year % 4 == 0)
                && (year % 100 != 0);// 采用布尔数据计算判断是否能整除
        return yearleap;
    }

    /**获取上n个小时整点小时时间
     * @param date
     * @return
     */
    public static String getLastHourTime(Date date, int n){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY)-n);
        date = ca.getTime();
        return sdf.format(date);
    }
    /**获取当前时间的整点小时时间
     * @param date
     * @return
     */
    public static long getCurrHourTime(Date date){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        date = ca.getTime();
        return getSecondTimestampTwo(date);
    }

    /**
     * 十一下加零
     *
     * @param str
     * @return
     */
    public String thanTen(int str) {

        String string = null;

        if (str < 10) {
            string = "0" + str;
        } else {

            string = "" + str;

        }
        return string;
    }

    /**
     * 计算时间差
     *
     * @param starTime 开始时间
     * @param endTime  结束时间
     * @return 返回时间差
     */
    public static String getTimeDifference(long starTime, long endTime) {
        String timeString = "";
        long diff = endTime - starTime;

        long day = diff / (24 * 60 * 60 * 1000);
        long hour = (diff / (60 * 60 * 1000) - day * 24);
        long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
        String hours = hour + "";
        String mins = min + "";
        String ss = s + "";
        if (hour < 10 && hour >= 0) {
            hours = "0" + hour;
        } else if (hour < 0) {
            hours = "00";
        }
        if (min < 10 && min >= 0) {
            mins = "0" + min;
        } else if (min < 0) {
            mins = "00";
        }
        if (s < 10 && s >= 0) {
            ss = "0" + s;
        } else if (s < 0) {
            ss = "00";
        }
        timeString = hours + ":" + mins + ":" + ss;
        return timeString;
    }

    /**
     * 时间戳转时间(10位时间戳)
     * @param time
     * @return
     */
    public static String timestampToDate(long time) {
        String dateTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeLong = Long.valueOf(time);
        dateTime = simpleDateFormat.format(new Date(timeLong * 1000L));
        return dateTime;
    }

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static long getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Long.valueOf(timestamp);
    }


    public static long getTimeDiffSec(long starTime, long endTime) {
        long diff = endTime - starTime;
        long s = diff / 1000 ;
        return s;
    }

    /**
     * 计算相差的小时
     *
     * @param starTime
     * @param endTime
     * @return
     */
    public String getTimeDifferenceHour(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();
            String string = Long.toString(diff);

            float parseFloat = Float.parseFloat(string);

            float hour1 = parseFloat / (60 * 60 * 1000);

            timeString = Float.toString(hour1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }

    /**
     * 获取时间中的某一个时间点
     *
     * @param str
     * @param type
     * @return
     */
    public String getJsonParseShiJian(String str, int type) {
        String shijanString = null;
        String nian = str.substring(0, str.indexOf("-"));
        String yue = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
        String tian = str.substring(str.lastIndexOf("-") + 1, str.indexOf(" "));
        String shi = str.substring(str.indexOf(" ") + 1, str.lastIndexOf(":"));
        String fen = str.substring(str.lastIndexOf(":") + 1, str.length());

        switch (type) {
            case 1:
                shijanString = nian;
                break;
            case 2:
                shijanString = yue;
                break;
            case 3:
                shijanString = tian;
                break;
            case 4:
                shijanString = shi;
                break;
            case 5:
                shijanString = fen;
                break;

        }
        return shijanString;
    }

    /**
     * Sring变int
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        int value = 0;
        value = Integer.parseInt(str);
        return value;
    }

    /**
     * 与当前时间比较早晚
     *
     * @param time 需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public boolean compareNowTime(String time) {
        boolean isDayu = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(getNowTime());

            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isDayu;
    }

    /**
     * 把时间戳变yyyy-MM-dd HH:mm格式时间
     *
     * @param time
     * @return
     */
    public static final String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     * 时间转换成时间戳
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() / 1000;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 比较两个时间
     *
     * @param starTime  开始时间
     * @param endString 结束时间
     * @return 结束时间大于开始时间返回true，否则反之֮
     */
    public boolean compareTwoTime(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }

    public static final long getNowZeroTime(){
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        return zero;
    }

    public boolean compareTwoTime2(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }

    /**
     * 获取年
     *
     * @param time
     * @return
     */
    public String getTimeYear(String time) {

        String substring = time.substring(0, time.lastIndexOf(" "));
        return substring;

    }

    /**
     * 换算小时，0.5小时-->0小时30分
     *
     * @param hour
     * @return
     */
    private String convertTime(String hour) {

        String substring = hour.substring(0, hour.lastIndexOf("."));
        String substring2 = hour.substring(hour.lastIndexOf(".") + 1,
                hour.length());
        substring2 = "0." + substring2;
        float f2 = Float.parseFloat(substring2);
        f2 = f2 * 60;
        String string = Float.toString(f2);
        String min = string.substring(0, string.lastIndexOf("."));
        return substring + "小时" + min + "分";

    }
}
