package com.cyanice.shop.repository;


import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT w.product FROM Wishlist w WHERE w.customer.id = :customerId")
    Page<Product> getCustomerWishlist(@Param("customerId") long customerId, Pageable pageable);

    @Query(
        "SELECT new com.cyanice.shop.dto.PopularProductDto(" +
        "   MAX(ol.product.id) AS id," +
        "   MAX(ol.product.name) AS name," +
        "   MAX(ol.product.description) AS description," +
        "   MAX(ol.product.price) AS price," +
        "   SUM(ol.quantity) AS qty" +
        ") " +
        "FROM   OrderLine ol " +
        "WHERE  ol.order.status = 'Paid' " +
        "AND    ol.order.lastModifiedDate BETWEEN :from AND :to " +
        "GROUP BY ol.product.id " +
        "ORDER BY qty DESC"
    )
    List<PopularProductDto> getPopularProducts(@Param("from") Instant from, @Param("to") Instant to);
}
