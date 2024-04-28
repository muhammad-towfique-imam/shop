-- Customer
insert into t_customer(name, email, address) values ('Customer1', 'c1@gmail.com', 'Mirpur') ;

-- Product
insert into t_product(name, description, price) values ('iPhone', 'Apple iPhone', 1000.0) ;
insert into t_product(name, description, price) values ('iPod', 'Apple iPod', 300.0) ;
insert into t_product(name, description, price) values ('iMac', 'Apple iMac', 3000.0) ;

-- Wishlist
insert into t_wishlist(customer_id, product_id, created_date) values (1, 1, '2004-04-25 00:00:00+06') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 2, '2024-04-26 00:00:00+06') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 3, '2024-04-27 00:00:00+06') ;

-- Order
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-04-25 00:00:00+06', '2024-04-26 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-04-27 00:00:00+06', '2024-04-27 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'NotPaid', '2024-04-27 00:00:00+06', '2024-04-27 00:00:00+06');

-- OrderLine
insert into t_order_line(order_id, unit_price, quantity, product_id) values (1, 900.00, 1, 1);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (1, 300.00, 1, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 600.00, 2, 1);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 300.00, 4, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 3000.00, 1, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (3, 1000.00, 1, 1);
