package com.cyanice.shop.enumeration;

import com.cyanice.shop.dto.DurationDto;

import java.time.Instant;

import static com.cyanice.shop.etc.DateUtil.*;

public enum SaleDuration {
    All,
    LastMonth;

    public DurationDto getDto() {
        return switch (this) {
            case All -> DurationDto.builder().from(getMinDate()).to(Instant.now()).build();
            case LastMonth -> DurationDto.builder().from(startOfPrevMonth()).to(endOfPrevMonth()).build();
        };
    }
}
