package com.cyanice.shop.controller;


import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private ProductService productService;

    @Autowired
    public CustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/wishlist")
    public List<ProductDto> getUserWishlist(@PathVariable long id) {
        return productService.getCustomerWishlist(id);
    }
}
