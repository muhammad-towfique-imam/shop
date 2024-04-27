package com.cyanice.shop.repository;


import com.cyanice.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT w.product FROM Wishlist w WHERE w.customer.id = :customerId")
    List<Product> getCustomerWishlist(@Param("customerId") long customerId);
}
