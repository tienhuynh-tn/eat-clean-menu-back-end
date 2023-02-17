package com.happy3friends.eatcleanmenubackend.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static ZonedDateTime getZoneDateTimeNow() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Ho_Chi_Minh"));
    }

    public static Date convertZoneDateTimeToDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getDateNow() {
        return convertZoneDateTimeToDate(getZoneDateTimeNow());
    }

    public static Timestamp getTimestampNow() {
        return new Timestamp(convertZoneDateTimeToDate(getZoneDateTimeNow()).getTime());
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date convertStringToDate(String strDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkDateBetweenMinMax(Date min, Date max, Date date) {
        return date.after(addDays(min, -1)) && date.before(addDays(max, 1));
    }
}