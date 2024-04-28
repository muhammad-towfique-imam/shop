package com.cyanice.shop.repository;

import com.cyanice.shop.dto.MaxSaleDateDto;
import com.cyanice.shop.entity.Customer;
import com.cyanice.shop.entity.Order;
import com.cyanice.shop.entity.OrderLine;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.enumeration.OrderStatus;
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
import java.util.stream.Collectors;

import static com.cyanice.shop.etc.DateUtil.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void OrderRepository_GetTotalSale_ReturnAmount() {
        Instant today = Instant.now();

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
                .createdDate(today)
                .lastModifiedDate(today)
                .build();
        List<OrderLine> lines = Arrays.asList(
            OrderLine.builder().order(o1).product(p1).unitPrice(p1.getPrice()).quantity(1).build(),
            OrderLine.builder().order(o1).product(p2).unitPrice(p2.getPrice()).quantity(3).build(),
            OrderLine.builder().order(o1).product(p3).unitPrice(p3.getPrice()).quantity(1).build()
        );
        o1.setLines(lines);
        orderRepository.save(o1);

        double totalSaleInput = o1.getLines().stream().map(ol -> ol.getQuantity() * ol.getUnitPrice()).collect(Collectors.summingDouble(Double::doubleValue));

        double totalSaleOutput = orderRepository.getTotalSale(startOfDay(today), endOfDay(today));

        Assertions.assertThat(totalSaleOutput).isEqualTo(totalSaleInput);
    }

    @Test
    public void OrderRepository_GetMaxSale_ReturnAmountWithDate() {
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

        double totalSale = o1.getLines().stream().map(ol -> ol.getQuantity() * ol.getUnitPrice()).collect(Collectors.summingDouble(Double::doubleValue));

        MaxSaleDateDto dto = orderRepository.getMaxSale(startOfDay(from), endOfDay(to));

        Assertions.assertThat(dto.getDate()).isEqualTo(instantToLocalDate(from));
        Assertions.assertThat(dto.getTotal()).isEqualTo(totalSale);
    }

}
