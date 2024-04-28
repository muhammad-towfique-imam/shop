package com.cyanice.shop.service.impl;

import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.dto.ProductDto;
import com.cyanice.shop.dto.WishlistResponse;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.enumeration.SaleDuration;
import com.cyanice.shop.repository.ProductRepository;
import com.cyanice.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public WishlistResponse getCustomerWishlist(long id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.getCustomerWishlist(id, pageable);
        List<Product> list = products.getContent();
        List<ProductDto> content = list.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        WishlistResponse response = new WishlistResponse();
        response.setContent(content);
        response.setPageNo(products.getNumber());
        response.setPageSize(products.getSize());
        response.setTotalElements(products.getTotalElements());
        response.setTotalPages(products.getTotalPages());
        response.setLast(products.isLast());

        return response;
    }

    @Override
    public List<PopularProductDto> getPopularProducts(SaleDuration duration) {
        return productRepository.getPopularProducts(duration.getDto().getFrom(), duration.getDto().getTo(), 3);
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
