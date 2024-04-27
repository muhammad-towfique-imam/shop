package com.cyanice.shop.repository;

import com.cyanice.shop.entity.Customer;
import com.cyanice.shop.entity.Product;
import com.cyanice.shop.entity.Wishlist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class WishlistRepositoryTests {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void WishlistRepository_SaveAll_ReturnSavedWishlist() {
        Customer c1 = Customer.builder().name("Customer1").email("c1@gmail.com").build();
        customerRepository.save(c1);

        Product p1 = Product.builder().name("iPhone").price(1000.0).build();
        productRepository.save(p1);

        Wishlist wishlist = Wishlist.builder().customer(c1).product(p1).build();
        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        Assertions.assertThat(savedWishlist).isNotNull();
        Assertions.assertThat(savedWishlist.getId()).isGreaterThan(0);
    }

    @Test
    public void WishlistRepository_CustomerWishlist_ReturnMoreThenOneProduct() {
        Customer c1 = Customer.builder().name("Customer1").email("c1@gmail.com").build();
        customerRepository.save(c1);

        Product p1 = Product.builder().name("iPhone").price(1000.0).build();
        Product p2 = Product.builder().name("iPod").price(300.0).build();
        Product p3 = Product.builder().name("iMac").price(3000.0).build();
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        Wishlist w1 = Wishlist.builder().customer(c1).product(p1).build();
        Wishlist w2 = Wishlist.builder().customer(c1).product(p2).build();
        Wishlist w3 = Wishlist.builder().customer(c1).product(p3).build();
        wishlistRepository.save(w1);
        wishlistRepository.save(w2);
        wishlistRepository.save(w3);

        List<Product> productList = productRepository.getCustomerWishlist(c1.getId());

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(3);
    }

}
