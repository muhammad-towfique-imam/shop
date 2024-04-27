package com.cyanice.shop.service;

import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.repository.ProductRepository;
import com.cyanice.shop.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void ProductService_CustomerWishlist_ReturnMoreThenOneProduct() {
        List<Product> inputList = Arrays.asList(
            Product.builder().name("iPhone").price(1000.0).build(),
            Product.builder().name("iPod").price(300.0).build(),
            Product.builder().name("iMac").price(3000.0).build()
        );

        when(productRepository.getCustomerWishlist(1)).thenReturn(inputList);

        List<ProductDto> outputList = productService.getCustomerWishlist(1);

        Assertions.assertThat(outputList).isNotNull();
        Assertions.assertThat(outputList.size()).isEqualTo(3);
    }

}
