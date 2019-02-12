package cn.itcast.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date,String patt){
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String str = sdf.format(date);
        return str;
    }

    public static Date StringToDate(String date ,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date date1 = sdf.parse(date);
        return date1;
    }

}
