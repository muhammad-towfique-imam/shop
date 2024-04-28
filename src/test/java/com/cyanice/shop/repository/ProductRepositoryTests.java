package com.cyanice.shop.repository;

import com.cyanice.shop.dto.PopularProductDto;
import com.cyanice.shop.entity.Customer;
import com.cyanice.shop.entity.Order;
import com.cyanice.shop.entity.OrderLine;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.enumeration.OrderStatus;
import com.cyanice.shop.enumeration.SaleQueryCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void ProductRepository_SaveAll_ReturnSavedProduct() {
        Product product = Product.builder().name("iPhone").price(1000.0).build();
        Product savedProduct = productRepository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepository_PopularProductsByCount_ReturnProductList() {
        Instant to = Instant.now();
        Instant from = to.minus(20, ChronoUnit.DAYS);

        Customer c1 = Customer.builder().name("Customer1").email("c1@gmail.com").build();
        customerRepository.save(c1);

        Product p1 = Product.builder().name("iPhone").price(1000.0).build();
        productRepository.save(p1);

        Product p2 = Product.builder().name("iPod").price(300.0).build();
        productRepository.save(p2);

        Product p3 = Product.builder().name("iMac").price(3000.0).build();
        productRepository.save(p3);

        Order o1 = Order.builder()
                .customer(c1)
                .status(OrderStatus.Paid)
                .createdDate(from)
                .lastModifiedDate(from)
                .build();
        // o1 is the order with max sale
        List<OrderLine> lines1 = Arrays.asList(
                OrderLine.builder().order(o1).product(p1).unitPrice(p1.getPrice()).quantity(1).build(),
                OrderLine.builder().order(o1).product(p2).unitPrice(p2.getPrice()).quantity(3).build(),
                OrderLine.builder().order(o1).product(p3).unitPrice(p3.getPrice()).quantity(1).build()
        );
        o1.setLines(lines1);
        orderRepository.save(o1);

        Order o2 = Order.builder()
                .customer(c1)
                .status(OrderStatus.Paid)
                .createdDate(to)
                .lastModifiedDate(to)
                .build();
        List<OrderLine> lines2 = Arrays.asList(
                OrderLine.builder().order(o2).product(p1).unitPrice(p1.getPrice()).quantity(1).build(),
                OrderLine.builder().order(o2).product(p2).unitPrice(p2.getPrice()).quantity(2).build(),
                OrderLine.builder().order(o2).product(p3).unitPrice(p3.getPrice()).quantity(1).build()
        );
        o2.setLines(lines2);
        orderRepository.save(o2);

        List<PopularProductDto> list = productRepository.getPopularProductsByCount(from, to, 2);

        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }
}
