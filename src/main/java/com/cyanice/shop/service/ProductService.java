package com.cyanice.shop.service;

import com.cyanice.shop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getCustomerWishlist(long id);
}
