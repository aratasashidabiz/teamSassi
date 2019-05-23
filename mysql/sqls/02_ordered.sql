USE mbshop;

DROP TABLE IF EXISTS ordered;
CREATE TABLE ordered (
    ordered_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    zip_code CHAR(8) NOT NULL,
    address VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(15) NOT NULL,
    updated_date DATE,
    created_date DATE
);