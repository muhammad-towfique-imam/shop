package com.cyanice.shop.service;

import com.cyanice.shop.dto.MaxSaleDateDto;

import java.time.Instant;

public interface OrderService {
    double getTotalSale(Instant from, Instant to);

    MaxSaleDateDto getMaxSale(Instant from, Instant to);
}
