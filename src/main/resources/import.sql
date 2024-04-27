-- Customer
insert into t_customer(name, email, address) values ('Customer1', 'c1@gmail.com', 'Mirpur') ;

-- Product
insert into t_product(name, description, price) values ('iPhone', 'Apple iPhone', 1000.0) ;
insert into t_product(name, description, price) values ('iPod', 'Apple iPod', 300.0) ;
insert into t_product(name, description, price) values ('iMac', 'Apple iMac', 3000.0) ;

-- Wishlist
insert into t_wishlist(customer_id, product_id, created_date) values (1, 1, '2024-04-25') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 2, '2024-04-26') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 3, '2024-04-27') ;