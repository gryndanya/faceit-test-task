CREATE TABLE IF NOT EXISTS test.orders
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    total_price     DECIMAL(10, 2) 	     NOT NULL,
    main_courses_id BIGINT,
    desserts_id     BIGINT,
    drinks_id       BIGINT,
    ice             BOOLEAN,
    lemon           BOOLEAN,
    CONSTRAINT  order_pk PRIMARY KEY (id),
    CONSTRAINT orders_main_courses_id_fk FOREIGN KEY (main_courses_id) REFERENCES test.main_courses(id),
    CONSTRAINT orders_desserts_id_fk FOREIGN KEY (desserts_id) REFERENCES test.desserts(id),
    CONSTRAINT orders_drinks_id_fk FOREIGN KEY (drinks_id) REFERENCES test.drinks(id)
);

INSERT INTO test.orders(total_price, main_courses_id, desserts_id, drinks_id, ice, lemon) VALUES (210, 1, null, null, null, null);
INSERT INTO test.orders(total_price, main_courses_id, desserts_id, drinks_id, ice, lemon) VALUES (630, 1, 1, 1, true, true);
INSERT INTO test.orders(total_price, main_courses_id, desserts_id, drinks_id, ice, lemon) VALUES (520, null, null, 1, false, false);