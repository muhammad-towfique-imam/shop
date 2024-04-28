-- Customer
insert into t_customer(name, email, address) values ('Torrey Veum', 'isidro_von@hotmail.com', 'Switzerland') ;

-- Product
insert into t_product(name, description, price) values ('iPhone X', 'SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...', 899.0) ;
insert into t_product(name, description, price) values ('iPhone 9', 'An apple mobile which is nothing like apple', 549.0) ;
insert into t_product(name, description, price) values ('MacBook Pro', 'MacBook Pro 2021 with mini-LED display may launch between September, November', 1749.0) ;
insert into t_product(name, description, price) values ('Huawei P30', 'Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.', 499.0) ;
insert into t_product(name, description, price) values ('Microsoft Surface Laptop 4', 'Style and speed. Stand out on HD video calls backed by Studio Mics. Capture ideas on the vibrant touchscreen.', 1499.0) ;
insert into t_product(name, description, price) values ('Brown Perfume', 'Royal_Mirage Sport Brown Perfume for Men & Women - 120ml', 40.0) ;
insert into t_product(name, description, price) values ('Samsung Galaxy Book', 'Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched', 1499.0) ;

-- Wishlist
insert into t_wishlist(customer_id, product_id, created_date) values (1, 1, '2004-04-25 00:00:00+06') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 2, '2024-04-26 00:00:00+06') ;
insert into t_wishlist(customer_id, product_id, created_date) values (1, 3, '2024-04-27 00:00:00+06') ;

-- Order
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-04-25 00:00:00+06', '2024-04-26 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-04-27 00:00:00+06', '2024-04-27 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'NotPaid', '2024-04-27 00:00:00+06', '2024-04-27 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-03-17 00:00:00+06', '2024-03-17 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-02-11 00:00:00+06', '2024-02-11 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-02-12 00:00:00+06', '2024-02-12 00:00:00+06');
insert into t_order(customer_id, order_status, created_date, last_modified_date) values (1, 'Paid', '2024-02-13 00:00:00+06', '2024-02-13 00:00:00+06');

-- OrderLine
insert into t_order_line(order_id, unit_price, quantity, product_id) values (1, 900.00, 1, 1);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (1, 300.00, 1, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 600.00, 2, 1);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 300.00, 4, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (2, 3000.00, 1, 2);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (3, 1000.00, 1, 1);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (4, 1499.00, 1, 7);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (5, 40.00, 2, 6);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (6, 1499.00, 1, 5);
insert into t_order_line(order_id, unit_price, quantity, product_id) values (7, 499.00, 1, 4);
