package com.cyanice.shop.service;

import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.repository.ProductRepository;
import com.cyanice.shop.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

        Pageable pageInfo = PageRequest.of(1, 10);
        when(productRepository.getCustomerWishlist(1, pageInfo)).thenReturn(new PageImpl<>(inputList));

        WishlistResponse response = productService.getCustomerWishlist(1, 1, 10);

        Assertions.assertThat(response.getContent()).isNotNull();
        Assertions.assertThat(response.getContent().size()).isEqualTo(3);
    }

}
