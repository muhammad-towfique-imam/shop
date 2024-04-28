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
    String POPULAR_PRODUCT_QUERY = "SELECT new com.cyanice.shop.dto.PopularProductDto(" +
            "   MAX(ol.product.id) AS id," +
            "   MAX(ol.product.name) AS name," +
            "   MAX(ol.product.description) AS description," +
            "   MAX(ol.product.price) AS price," +
            "   SUM(ol.quantity) AS count," +
            "   SUM(ol.quantity * ol.unitPrice) AS amount" +
            ") " +
            "FROM   OrderLine ol " +
            "WHERE  ol.order.status = 'Paid' " +
            "AND    ol.order.lastModifiedDate BETWEEN :from AND :to " +
            "GROUP BY ol.product.id ";
    // Tried to pass sort column as named param. It was showing wrong result. hence opted for this messy solution :(
    @Query(POPULAR_PRODUCT_QUERY + " ORDER BY count DESC LIMIT :limit")
    List<PopularProductDto> getPopularProductsByCount(
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("limit") int limit);

    @Query(POPULAR_PRODUCT_QUERY + " ORDER BY amount DESC LIMIT :limit")
    List<PopularProductDto> getPopularProductsByAmount(
            @Param("from") Instant from,
            @Param("to") Instant to,
            @Param("limit") int limit);
}
