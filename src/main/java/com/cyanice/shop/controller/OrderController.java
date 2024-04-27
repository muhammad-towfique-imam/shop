package com.cyanice.shop.controller;


import com.cyanice.shop.dto.CurrentSaleDto;
import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.enumeration.SaleDuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @GetMapping("/current-sale")
    public CurrentSaleDto currentSale() {
        return CurrentSaleDto.builder().date(Instant.now()).total(100.0).build();
    }

    @GetMapping("/max-sale-date")
    public MaxSaleDateDto maxSaleDate() {
        return MaxSaleDateDto.builder().date(Instant.now()).total(100.0).build();
    }

    @GetMapping("/popular-products")
    public List<ProductDto> popularProducts(@RequestParam(name = "duration", required = false, defaultValue = "All") SaleDuration duration) {
        return new ArrayList<>();
    }
}
