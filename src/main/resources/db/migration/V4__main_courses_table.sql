CREATE TABLE IF NOT EXISTS test.main_courses
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(50) UNIQUE    NOT NULL,
    price            DECIMAL(10, 2) 	   NOT NULL,
    cuisine_types_id BIGINT   	           NOT NULL,
    CONSTRAINT main_course_pk PRIMARY KEY (id),
    CONSTRAINT main_courses_cuisine_types_id_fk FOREIGN KEY (cuisine_types_id) REFERENCES test.cuisine_types(id)
);

INSERT INTO test.main_courses(name, price, cuisine_types_id) VALUES ('pasta1', 210, 1);
INSERT INTO test.main_courses(name, price, cuisine_types_id) VALUES ('pasta2', 220, 2);
INSERT INTO test.main_courses(name, price, cuisine_types_id) VALUES ('pasta3', 230, 3);