package com.cyanice.shop.controller;


import com.cyanice.shop.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @GetMapping("/{id}/wishlist")
    public List<ProductDto> getUserWishlist(@PathVariable long id) {
        return Arrays.asList(
                ProductDto.builder().name("iPhone").price(1000.0).build(),
                ProductDto.builder().name("iPod").price(300.0).build(),
                ProductDto.builder().name("iMac").price(3000.0).build()
        );
    }
}
