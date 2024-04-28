package com.cyanice.shop.controller;


import com.cyanice.shop.dto.CurrentSaleDto;
import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.enumeration.SaleDuration;
import com.cyanice.shop.enumeration.SaleQueryCategory;
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
import static com.cyanice.shop.etc.Validator.checkDateRange;

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
            @RequestParam(name = "on", required = false) @DateTimeFormat(pattern = API_DATE_FMT) LocalDate on) {
        log.info("Inside OrderController()");
        log.debug(String.format("Parameters = (date) -> %s", on));
        Instant instantDate = on != null? localDateToInstant(on): Instant.now();
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
        checkDateRange(fromInstant, toInstant);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getMaxSale(fromInstant, toInstant));
    }

    @GetMapping("/popular-products")
    public ResponseEntity<List<PopularProductDto>> popularProducts(
            @RequestParam(name = "category") SaleQueryCategory category,
            @RequestParam(name = "duration", required = false, defaultValue = "All") SaleDuration duration) {
        return new ResponseEntity<>(
                productService.getPopularProducts(category, duration),
                HttpStatus.OK
        );
    }
}
