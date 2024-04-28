package com.cyanice.shop.controller;

import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.enumeration.SaleDuration;
import com.cyanice.shop.service.OrderService;
import com.cyanice.shop.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.cyanice.shop.etc.DateUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;

    @Test
    public void OrderController_GetSaleForToday_ReturnAmountWithDate() throws Exception {
        Instant today = Instant.now();
        String dateStr = instantToString(today);
        double totalSale = 6500.0;

        when(orderService.getTotalSale(any(), any())).thenReturn(totalSale);

        ResultActions response = mockMvc.perform(get("/api/order/sale-on-date")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(dateStr)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.is(totalSale)));
    }


    @Test
    public void OrderController_GetMaxSaleDate_ReturnTotalWithDate() throws Exception {
        String fromStr = "2024-04-20";
        Instant from = strToInstant(fromStr);

        String toStr = "2024-04-26";
        Instant to = strToInstant(toStr);

        var dto = new MaxSaleDateDto(2024, 04, 22, 3000.0);

        when(orderService.getMaxSale(from, to)).thenReturn(dto);

        String url = String.format("/api/order/max-sale-date?from=%s&to=%s", fromStr, toStr);
        ResultActions response = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is(localDateToStr(dto.getDate()))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.is(dto.getTotal())));
    }

    @Test
    public void OrderController_GetMaxSaleDate_ReturnNotFoundStatus() throws Exception {
        String fromStr = "2024-04-20";
        Instant from = strToInstant(fromStr);

        String toStr = "2024-04-26";
        Instant to = strToInstant(toStr);

        when(orderService.getMaxSale(from, to)).thenReturn(null);

        String url = String.format("/api/order/max-sale-date?from=%s&to=%s", fromStr, toStr);
        ResultActions response = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void OrderController_GetPopularProducts_ReturnZeroOrMoreProducts() throws Exception {
        List<PopularProductDto> products = Arrays.asList(
                PopularProductDto.builder().name("iPhone").price(1000.0).saleCount(50).build(),
                PopularProductDto.builder().name("iPod").price(300.0).saleCount(40).build(),
                PopularProductDto.builder().name("iMac").price(3000.0).saleCount(30).build()
        );

        when(productService.getPopularProducts(SaleDuration.All)).thenReturn(products);

        ResultActions response = mockMvc.perform(get("/api/order/popular-products")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void OrderController_GetLastMonthPopularProducts_ReturnZeroOrMoreProducts() throws Exception {
        List<PopularProductDto> products = Arrays.asList(
                PopularProductDto.builder().name("iPhone").price(1000.0).saleCount(50).build(),
                PopularProductDto.builder().name("iPod").price(300.0).saleCount(40).build(),
                PopularProductDto.builder().name("iMac").price(3000.0).saleCount(30).build()
        );

        when(productService.getPopularProducts(SaleDuration.LastMonth)).thenReturn(products);

        ResultActions response = mockMvc.perform(get("/api/order/popular-products?duration=LastMonth")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

}
