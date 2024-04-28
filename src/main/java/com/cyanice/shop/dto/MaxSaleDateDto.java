package com.cyanice.shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaxSaleDateDto {
    private LocalDate date;
    private Double total;

    public MaxSaleDateDto(int year, int month, int day, double total) {
        this.date =  LocalDate.of(year, month, day);
        this.total = total;
    }
}
