package com.cyanice.shop.controller;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void OrderController_GetSaleForToday_ReturnNonNegativeAmount() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/order/current-sale")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", Matchers.greaterThan(-1.0)));
    }

    @Test
    public void OrderController_GetMaxSaleDate_ReturnNotNullInstant() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/order/max-sale-date")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.maxSaleDate").isNotEmpty());
    }

    @Test
    public void OrderController_GetPopularProducts_ReturnZeroOrMoreProducts() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/order/popular-products")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.greaterThan(-1)));
    }

    @Test
    public void OrderController_GetLastMonthPopularProducts_ReturnZeroOrMoreProducts() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/order/popular-products?duration=LastMonth")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.greaterThan(-1)));
    }

}
