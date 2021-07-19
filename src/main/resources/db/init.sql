CREATE DATABASE productdatabase;

CREATE TABLE product (id BIGINT NOT NULL,
                      name VARCHAR (50) NOT NULL,
                      description VARCHAR (100),
                      weight FLOAT NOT NULL,
                      price FLOAT NOT NULL,
                      country VARCHAR (50),
                      PRIMARY KEY (id));

CREATE INDEX index_name_product ON product(name);
