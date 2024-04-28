package com.cyanice.shop.etc;

import com.cyanice.shop.exception.InvalidDateRangeException;
import com.cyanice.shop.exception.NoDataException;

import java.time.Instant;

import static com.cyanice.shop.etc.DateUtil.getMinDate;

public class Validator {
    public static void checkEmpty(Object o) {
        if (o == null) {
            throw new NoDataException();
        }
    }
    public static void checkDateRange(Instant from, Instant to) {
        Instant minDate = getMinDate();
        if (from == null || to == null || from.isBefore(minDate) || to.isBefore(minDate) || to.isBefore(from)) {
            throw new InvalidDateRangeException();
        }
    }
}
