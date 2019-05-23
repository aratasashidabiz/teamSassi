USE mbshop;

DROP TABLE IF EXISTS ordered_detail;
CREATE TABLE ordered_detail (
    ordered_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    ordered_id INT NOT NULL,
    product_id INT NOT NULL,
    total_price INT CHECK ( total_price > 0 ),
    product_quantity INT CHECK ( product_quantity > 0 ),
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ordered_id
        FOREIGN KEY(ordered_id) REFERENCES ordered (ordered_id) ON DELETE RESTRICT,
    CONSTRAINT fk_product_id
        FOREIGN KEY(product_id) REFERENCES product (product_id) ON DELETE RESTRICT
);

INSERT INTO ordered_detail(ordered_id, product_id, total_price, product_quantity) VALUES
                (1, 2, 1030, 1),(3, 3, 1080, 5),(2, 10, 3000, 2),(5, 5, 1300, 4),(7, 2, 8600, 2),(5, 4, 4040, 1),(8, 3, 2000, 3),(9, 1, 100, 1),(2, 4, 1600, 2),(1, 2, 1200, 1);