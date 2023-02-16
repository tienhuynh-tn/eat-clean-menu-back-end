package com.happy3friends.eatcleanmenubackend.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
}