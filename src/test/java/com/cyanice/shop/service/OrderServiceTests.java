package com.cyanice.shop.service;

import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.repository.OrderRepository;
import com.cyanice.shop.service.impl.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.cyanice.shop.etc.DateUtil.endOfDay;
import static com.cyanice.shop.etc.DateUtil.startOfDay;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void OrderService_GetTotalSale_ReturnAmount() {
        Instant to = Instant.now();
        Instant from = to.minus(20, ChronoUnit.DAYS);
        double inputAmount = 3600.0;

        when(orderRepository.getTotalSale(startOfDay(from), endOfDay(to))).thenReturn(inputAmount);

        double outputAmount = orderService.getTotalSale(from, to);

        Assertions.assertThat(inputAmount).isEqualTo(outputAmount);
    }

    @Test
    public void OrderService_GetMaxSale_ReturnAmountWithDate() {
        Instant to = Instant.now();
        Instant from = to.minus(20, ChronoUnit.DAYS);
        var inputDto = new MaxSaleDateDto(2024, 04, 22, 3000.0);

        when(orderRepository.getMaxSale(startOfDay(from), endOfDay(to))).thenReturn(inputDto);

        var outputDto = orderService.getMaxSale(from, to);

        Assertions.assertThat(inputDto.getTotal()).isEqualTo(outputDto.getTotal());
        Assertions.assertThat(inputDto.getDate()).isEqualTo(outputDto.getDate());
    }

}
