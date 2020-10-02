package com.haenandong.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTest {

    /**
     * 날짜변환시 참고
     *
    ■ String → java.sql.Date 변환
    String  day = "2016-11-22"; // 형식을 지켜야 함
    java.sql.Date d = java.sql.Date.valueOf(day);

    ■ String → java.sql.Timestamp 변환
    String day = "2016-11-22 11:22:30.0"; // 형식을 지켜야 함
    java.sql.Timestamp t = java.sql.Timestamp.valueOf(day);

    ■ String → java.util.Date 변환
    String  day = "20161122"; // SimpleDateFormat 생성자에 전달되는 형식과 일치해야 함
    java.util.Date d = new java.text.SimpleDateFormat("yyyyMMdd").parse(day);

    ■ java.util.Date → java.sql.Date 변환
    java.util.Date u = new java.util.Date();
    java.sql.Date s = new java.sql.Date(u.getTime());
    */

    // String -> java.sql.Date로 변경
    public static void main(String[] args) {
        DateTest dt = new DateTest();

        System.out.println(dt.transformDate("2020", "11", "22"));
        System.out.println(dt.transformDate("20201101"));

        System.out.println(dt.dateStringTodateString("20200921", "yyyyMMdd", "yyMMdd"));
    }

    // 년, 월, 일이 각각 입력되었을 경우 Date로 변경하는 메서드
    public Date transformDate(String year, String month, String day) {
        String date = year+"-"+month+"-"+day;
        java.sql.Date d = java.sql.Date.valueOf(date);

        return d;
    }

    // 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
    public Date transformDate(String date) {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");

        java.util.Date tempDate = null;

        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);

        // 반환된 String 값을 Date로 변경한다.
        java.sql.Date d = java.sql.Date.valueOf(transDate);

        return d;
    }

    /**
     * 날짜문자열을 원하는포맷의 문자열로 변환
     * @param dateString    "20200921"
     * @param beforeFormat  "yyyyMMdd"
     * @param afterFormat   "yyMMdd"
     * @return String
     */

    public String dateStringTodateString (String dateString, String beforeFormat, String afterFormat) {

        String convertDate = "";

        try {
            String day = dateString; // SimpleDateFormat 생성자에 전달되는 형식과 일치해야 함
            Date d = new SimpleDateFormat(beforeFormat).parse(day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(afterFormat);
            convertDate = simpleDateFormat.format(d);
            System.out.println("result Date : " + convertDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }
}
