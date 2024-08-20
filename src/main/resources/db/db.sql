CREATE DATABASE javaee_pos;

USE javaee_pos;

CREATE TABLE customer (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(255),
                          contact INT
);

CREATE TABLE item (
                      item_id INT PRIMARY KEY AUTO_INCREMENT,
                      description VARCHAR(255),
                      unit_price DOUBLE,
                      qty INT
);

CREATE TABLE orders (
                        order_id INT PRIMARY KEY AUTO_INCREMENT,
                        date DATE,
                        discount INT,
                        total DOUBLE,
                        customer_id INT,
                        FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE order_details (
                               order_id INT,
                               item_id INT,
                               qty INT,
                               PRIMARY KEY (order_id, item_id),
                               FOREIGN KEY (order_id) REFERENCES orders(order_id),
                               FOREIGN KEY (item_id) REFERENCES item(item_id)
);
