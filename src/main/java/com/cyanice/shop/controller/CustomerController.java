package com.cyanice.shop.controller;


import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private ProductService productService;

    @Autowired
    public CustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/wishlist")
    public ResponseEntity<WishlistResponse> getUserWishlist(
            @PathVariable long id,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(productService.getCustomerWishlist(id, pageNo, pageSize), HttpStatus.OK);
    }
}
