USE mbshop;

DROP TABLE IF EXISTS ordered;
CREATE TABLE ordered (
    ordered_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255) NOT NULL,
    zip_code CHAR(8) NOT NULL,
    address VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(15) NOT NULL,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO ordered(customer_name, zip_code, address, telephone_number) VALUES
                ('sassy', '123-2345', '沖縄', '090-2345-2345'),('どいっち', '323-3625', '大根県', '090-2345-2345'),
                ('DON!じゃら', '333-3453', '金沢', '232-2755-2245'),('JOJO', '123-2345', '日本', '090-1111-2643'),
                ('sassy', '123-2345', '沖縄', '090-2345-2345'),('おっくん', '323-3625', '山口', '090-2345-2345'),
                ('ロンディー', '333-3453', '香港', '232-2755-2245'),('コウササン', '123-2345', 'アメリカ', '090-1111-2643'),
                ('キッシー', '333-3453', 'カナダ', '232-2755-2245'),('益荒男', '123-2345', '火星', '090-1111-2643');