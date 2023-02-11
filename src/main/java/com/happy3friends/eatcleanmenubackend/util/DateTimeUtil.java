package com.happy3friends.eatcleanmenubackend.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {
    public static ZonedDateTime getZoneDateTimeNow() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Ho_Chi_Minh"));
    }
}