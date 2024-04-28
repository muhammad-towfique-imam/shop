package com.cyanice.shop.etc;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static final String API_DATE_FMT = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(API_DATE_FMT);

    public static LocalDate strToLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public static String localDateToStr(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static Instant strToInstant(String date) {
        return localDateToInstant(LocalDate.parse(date, FORMATTER));
    }

    public static String instantToString(Instant date) {
        return localDateToStr(instantToLocalDate(date));
    }

    public static Instant getMinDate() {
        return localDateToInstant(LocalDate.of(2000, 1, 1));
    }

    public static Instant localDateToInstant(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDate instantToLocalDate(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Instant startOfDay(Instant date) {
        return startOfDay(date.atZone(ZoneId.systemDefault())).toInstant();
    }

    public static Instant endOfDay(Instant date) {
        return endOfDay(date.atZone(ZoneId.systemDefault())).toInstant();
    }

    public static Instant startOfPrevMonth() {
        LocalDate localDate = YearMonth.now().minusMonths(1).atDay(1);
        return localDateToInstant(localDate);
    }
    public static Instant endOfPrevMonth() {
        LocalDate localDate = YearMonth.now().atDay(1).minusDays(1);
        return endOfDay(localDateToInstant(localDate));
    }

    public static ZonedDateTime startOfDay(ZonedDateTime date) {
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    public static ZonedDateTime endOfDay(ZonedDateTime date) {
        return date.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusSeconds(1);
    }
}
