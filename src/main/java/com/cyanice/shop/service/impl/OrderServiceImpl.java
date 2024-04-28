package com.cyanice.shop.service.impl;

import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.repository.OrderRepository;
import com.cyanice.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.cyanice.shop.etc.DateUtil.endOfDay;
import static com.cyanice.shop.etc.DateUtil.startOfDay;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public double getTotalSale(Instant from, Instant to) {
        Double total = orderRepository.getTotalSale(startOfDay(from), endOfDay(to));
        return total != null? total: 0.0;
    }

    @Override
    public MaxSaleDateDto getMaxSale(Instant from, Instant to) {
        return orderRepository.getMaxSale(startOfDay(from), endOfDay(to));
    }
}
