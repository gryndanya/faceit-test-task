CREATE TABLE IF NOT EXISTS orders
(
    id          BIGINT AUTO_INCREMENT      NOT NULL,
    name        VARCHAR(50)         UNIQUE NOT NULL,
    total_price DECIMAL(10, 2) 	           NOT NULL,
    CONSTRAINT  order_pk PRIMARY KEY (id)
);

