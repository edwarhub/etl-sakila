CREATE TABLE bi_city(
    city_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE bi_store(
    store_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE bi_language(
    language_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE bi_film(
    film_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE bi_date(
    date_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    year INT,
    month INT,
    day INT
);

CREATE TABLE bi_rental(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    store_id INT(10) UNSIGNED,
    date_id INT(10) UNSIGNED,
    city_id INT(10) UNSIGNED,
    language_id INT(10) UNSIGNED,
    film_id INT(10) UNSIGNED,
    quantity INT
);
 
ALTER TABLE bi_rental ADD CONSTRAINT fk_store FOREIGN KEY (store_id) REFERENCES bi_store(store_id);
ALTER TABLE bi_rental ADD CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES bi_city(city_id);
ALTER TABLE bi_rental ADD CONSTRAINT fk_language FOREIGN KEY (language_id) REFERENCES bi_language(language_id);
ALTER TABLE bi_rental ADD CONSTRAINT fk_film FOREIGN KEY (film_id) REFERENCES bi_film(film_id);
ALTER TABLE bi_rental ADD CONSTRAINT fk_date FOREIGN KEY (date_id) REFERENCES bi_date(date_id);
