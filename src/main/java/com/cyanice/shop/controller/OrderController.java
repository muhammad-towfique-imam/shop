package com.cyanice.shop.controller;


import com.cyanice.shop.dto.CurrentSaleDto;
import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.enumeration.SaleDuration;
import com.cyanice.shop.service.OrderService;
import com.cyanice.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static com.cyanice.shop.etc.DateUtil.*;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    private ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/sale-on-date")
    public ResponseEntity<CurrentSaleDto> saleOnDate(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = API_DATE_FMT) LocalDate date) {
        log.info(String.format("Inside OrderController()"));
        log.debug(String.format("Parameters = (date) -> %s", date));
        Instant instantDate = date != null? localDateToInstant(date): Instant.now();
        double total = orderService.getTotalSale(instantDate, instantDate);
        return new ResponseEntity<>(
                CurrentSaleDto.builder().date(instantToLocalDate(instantDate)).total(total).build(),
                HttpStatus.OK
        );
    }


    @GetMapping("/max-sale-date")
    public ResponseEntity<?> maxSaleDate(
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = API_DATE_FMT) LocalDate from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = API_DATE_FMT) LocalDate to) {
        Instant fromInstant = from != null? localDateToInstant(from): getMinDate();
        Instant toInstant = to != null? localDateToInstant(to): Instant.now();
        MaxSaleDateDto dto = orderService.getMaxSale(fromInstant, toInstant);
        return switch (dto) {
            case null -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the duration");
            default -> ResponseEntity.status(HttpStatus.OK).body(dto);
        };
    }

    @GetMapping("/popular-products")
    public ResponseEntity<List<PopularProductDto>> popularProducts(
            @RequestParam(name = "duration", required = false, defaultValue = "All") SaleDuration duration) {
        return new ResponseEntity<>(
                productService.getPopularProducts(duration),
                HttpStatus.OK
        );
    }
}
