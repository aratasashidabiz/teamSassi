USE mbshop;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    product_title VARCHAR(255) NOT NULL,
    product_price INT NOT NULL CHECK (product_id > 0),
    cast_name_list VARCHAR(255),
    director_name VARCHAR(255),
    description TEXT,
    updated_date DATE,
    created_date DATE
);