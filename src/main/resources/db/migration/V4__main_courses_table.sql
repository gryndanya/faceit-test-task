CREATE TABLE IF NOT EXISTS main_courses
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(50)           NOT NULL,
    price      DECIMAL(10, 2) 	     NOT NULL,
    cuisine_id BIGINT   	         NOT NULL,
    CONSTRAINT main_course_pk PRIMARY KEY (id)
);