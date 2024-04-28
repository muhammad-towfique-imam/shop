package com.cyanice.shop.service;

import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.enumeration.SaleDuration;

import java.util.List;

public interface ProductService {
    WishlistResponse getCustomerWishlist(long id, int pageNo, int pageSize);
    List<PopularProductDto> getPopularProducts(SaleDuration duration);
}
