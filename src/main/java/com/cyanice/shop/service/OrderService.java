package com.cyanice.shop.service;

import com.cyanice.shop.dto.MaxSaleDateDto;

import java.time.Instant;

public interface OrderService {
    /**
     * Gets total sale amount for a specified time period
     *
     * @param from The start date
     * @param to   The end date
     * @return the total sale amount for the specified period
     */
    double getTotalSale(Instant from, Instant to);

    /**
     * Gets the date (with sale amount) when the sale was maximum for a specified time period
     *
     * @param from The start date
     * @param to   The end date
     * @return MaxSaleDateDto witch contains the date and total sale
     */
    MaxSaleDateDto getMaxSale(Instant from, Instant to);
}
