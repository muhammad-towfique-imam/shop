package com.cyanice.shop.service.impl;

import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.repository.ProductRepository;
import com.cyanice.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getCustomerWishlist(long id) {
        return productRepository.getCustomerWishlist(id).stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

}
