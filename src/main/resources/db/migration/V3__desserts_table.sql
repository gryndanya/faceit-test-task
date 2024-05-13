CREATE TABLE IF NOT EXISTS desserts
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    price      DECIMAL(10, 2) 	     NOT NULL,
    cuisine_id BIGINT   	         NOT NULL,
    CONSTRAINT dessert_pk PRIMARY KEY (id),

);