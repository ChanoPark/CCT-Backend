package com.example.hackerton.global.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    private static final ZoneId KST_ZONE = ZoneId.of("Asia/Seoul");

    static DateTimeFormatter DATETIME_OF_SECOND_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static int toDaySeconds(int day) {
        return 3600 * 24 * day;
    }

    public static long toMinuteMillis(int minute) {
        return 60 * 1000L * minute;
    }

    public static long toDayMillis(int day) {
        return 3600 * 1000L * 24 * day;
    }

    public static long toHourMillis(int hour) {
        return 3600 * 1000L * hour;
    }

    public static long getNowTimestamp() {
        return Instant.now().getEpochSecond();
    }

    public static String getNowKST() {
        ZonedDateTime now = ZonedDateTime.now(KST_ZONE);
        return now.format(DATETIME_OF_SECOND_FORMAT);
    }

    public static long nextHourlyMinuteDurationMillis(int minute) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(1).truncatedTo(ChronoUnit.HOURS).plusMinutes(minute);
        Duration duration = Duration.between(start, end);
        return duration.toMillis();
    }

    public static long nextDailyHourDurationMillis(int hour) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1).truncatedTo(ChronoUnit.DAYS).plusHours(hour);
        Duration duration = Duration.between(start, end);
        return duration.toMillis();
    }

    public static long nowPlusMinuteMillis(int minute) {
        ZonedDateTime now = ZonedDateTime.now(KST_ZONE);
        return now.plusMinutes(minute).truncatedTo(ChronoUnit.SECONDS).toEpochSecond() * 1000;
    }

    public static long nowPlusHourMillis(int hour) {
        ZonedDateTime now = ZonedDateTime.now(KST_ZONE);
        return now.plusHours(hour).truncatedTo(ChronoUnit.SECONDS).toEpochSecond() * 1000;
    }

    public static long nowPlusDayMillis(int day) {
        ZonedDateTime now = ZonedDateTime.now(KST_ZONE);
        return now.plusDays(day).truncatedTo(ChronoUnit.SECONDS).toEpochSecond() * 1000;
    }

}