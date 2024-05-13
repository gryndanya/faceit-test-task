CREATE TABLE IF NOT EXISTS test.desserts
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(50) UNIQUE    NOT NULL,
    price            DECIMAL(10, 2) 	   NOT NULL,
    cuisine_types_id BIGINT   	           NOT NULL,
    CONSTRAINT dessert_pk PRIMARY KEY (id),
    CONSTRAINT desserts_cuisine_types_id_fk FOREIGN KEY (cuisine_types_id) REFERENCES test.cuisine_types(id)
);

INSERT INTO test.desserts(name, price, cuisine_types_id) VALUES ('cake1', 110, 1);
INSERT INTO test.desserts(name, price, cuisine_types_id) VALUES ('cake2', 120, 2);
INSERT INTO test.desserts(name, price, cuisine_types_id) VALUES ('cake3', 130, 3);