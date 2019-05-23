USE mbshop;

DROP TABLE IF EXISTS ordered_detail;
CREATE TABLE ordered_detail (
    ordered_detail_id SERIAL PRIMARY KEY,
    ordered_id INT NOT NULL,
    product_id INT NOT NULL,
    product_price INT CHECK ( product_price > 0 ),
    product_quantity INT CHECK ( product_quantity > 0 ),
    updated_date DATE,
    created_date DATE
#     CONSTRAINT fk_ordered_id
#         FOREIGN KEY(ordered_id) REFERENCES ordered (ordered_id) ON DELETE RESTRICT,
#     CONSTRAINT fk_product_id
#         FOREIGN KEY(product_id) REFERENCES product (product_id) ON DELETE RESTRICT
);