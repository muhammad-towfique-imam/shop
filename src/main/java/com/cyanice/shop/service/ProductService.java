package com.cyanice.shop.service;

import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.enumeration.SaleDuration;
import com.cyanice.shop.enumeration.SaleQueryCategory;

import java.util.List;

public interface ProductService {
    /**
     * Gets the paged wishlist for a customer
     *
     * @param id The id of the customer
     * @param pageNo The zero base page index
     * @param pageSize Positive integer indicating the page size
     * @return WishlistResponse witch contains the product list for the specified page
     */
    WishlistResponse getCustomerWishlist(long id, int pageNo, int pageSize);
    /**
     * Gets the popular products for the specified duration
     *
     * @param queryType The query type. Query can be done by total sale amount or total sale count
     * @param duration The duration of the query.
     * @return The top 5 products for the specified duration
     */
    List<PopularProductDto> getPopularProducts(SaleQueryCategory category, SaleDuration duration);
}
