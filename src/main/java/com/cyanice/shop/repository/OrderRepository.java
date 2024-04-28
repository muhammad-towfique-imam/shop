package com.cyanice.shop.repository;


import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT SUM(ol.quantity * ol.unitPrice) "
            + "FROM OrderLine ol "
            + "WHERE ol.order.status = 'Paid' "
            + "AND ol.order.lastModifiedDate BETWEEN :from AND :to")
    Double getTotalSale(@Param("from") Instant from, @Param("to") Instant to);

    @Query(
        "SELECT new com.cyanice.shop.dto.MaxSaleDateDto(" +
        "   EXTRACT(year FROM ol.order.lastModifiedDate) AS y," +
        "   EXTRACT(month FROM ol.order.lastModifiedDate) AS m," +
        "   EXTRACT(day FROM ol.order.lastModifiedDate) AS d," +
        "   SUM(ol.quantity * ol.unitPrice) AS total" +
        ") " +
        "FROM   OrderLine ol " +
        "WHERE  ol.order.status = 'Paid' " +
        "AND    ol.order.lastModifiedDate BETWEEN :from AND :to " +
        "GROUP BY d, m, y " +
        "ORDER BY total DESC " +
        "LIMIT 1"
    )
    MaxSaleDateDto getMaxSale(@Param("from") Instant from, @Param("to") Instant to);
}
