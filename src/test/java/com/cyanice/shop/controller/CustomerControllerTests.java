package com.cyanice.shop.controller;

import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void CustomerController_GetCustomerWishlist_ReturnWishlistNotEmpty() throws Exception {
        List<ProductDto> products = Arrays.asList(
            ProductDto.builder().name("iPhone").price(1000.0).build(),
            ProductDto.builder().name("iPod").price(300.0).build(),
            ProductDto.builder().name("iMac").price(3000.0).build()
        );
        WishlistResponse responseDto = WishlistResponse.builder().pageSize(10).last(true).pageNo(0).content(products).build();
        when(productService.getCustomerWishlist(1, 0, 10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/customer/1/wishlist")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(3)));
    }
}
