DROP TABLE IF EXISTS product;
CREATE TABLE product (
  PRODUCT_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(255),
  PRICE DOUBLE,
  IMG VARCHAR(255),
  DESCRIPTION VARCHAR(2000),
  UNITS_IN_STOCK INTEGER);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('PowerVision PowerRay Angler', 51000, 'PowerVisionAngler.jpeg', 'Dron podvodní, 4K kamera, vodotěsný do 30m, váha 3.8kg, v balení PoweRay, ovladač, nabíjecí stanice, PowerSekker Fishfinder, Bait Drop Line', 50);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('Power Vision PowerRay Wizard', 39900, 'PowerVisionWizard.jpeg', 'Dron podvodní, 4K kamera, vodotěsný do 30m, váha 3.8kg, v balení PoweRay, ovladač, nabíjecí stanice, PowerSeeker Fishfinder, Bait Drop Line, VR brýle', 50);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('DJI Mavic 2 Pro', 38900, 'Mavic2Pro.jpeg', 'Dron - 4K kamera Hasselblad s 3-osou stabilizací, snímač 1", 2x optický zoom, dosah až 8km, výdrž přibližně 31min, max. rychlost 72km/hod, 8GB vnitřní paměť, senzory pro detekci překážek, přistávací světlo, Activetrack', 100);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('DJI Phantom 4 Pro+ Obsidian Edition + DJI Goggles', 63000,'DJIPantom.jpeg', 'Dron - 4K kamera s 3-osou stabilizací 1 snímačem, dosah až 7km, výdrž přibližně 29min, max. rychlost 20m/s, hmotnost 1388g, GPS, microSD (max.128GB), magnesiový skelet, antikolizní čidla, funkce Tapfly a ActiveTrack, dálkové ovládání s displejem, brýle DJI Goggles', 44);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('RYZE Tello Boost Combo', 5000, 'TelloBoost.jpeg', 'Dron - micro selfie dron, vybaven vestavěnou HD kamerou s rozlišením videa 720/30p, 80g, přenos na vzdálenost 100m, varianta v Combo Boost balení', 120);

INSERT INTO product (NAME, PRICE, IMG, DESCRIPTION, UNITS_IN_STOCK)
  VALUES('Xiaomi Mi Drone Mini', 1300, 'xiaomi.jpeg', 'Dron s pokročilými funkcemi, Headless Mode, G-senzor, HD kamera, gyroskop, akcelerometr, optické snímače, funkce Altitude Hold, infračervený režim bitvy, dosah až 50m, délka letu 7-10 minut, spárování s aplikací v telefonu', 54);DROP TABLE IF EXISTS customer;

DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  CUSTOMER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME VARCHAR(50),
  LAST_NAME VARCHAR(100),
  EMAIL VARCHAR(100),
  TELEPHONE INTEGER);

DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card(
  CREDIT_CARD_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NUMBER VARCHAR(50),
  VALIDITY VARCHAR(30),
  CUSTOMER_ID BIGINT,
  FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID));

DROP TABLE IF EXISTS address;

CREATE TABLE address(
  ADDRESS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  COUNTRY VARCHAR(50),
  CITY VARCHAR(50),
  STREET VARCHAR(50),
  HOUSE_NUMBER INT,
  ZIP INT);

DROP TABLE IF EXISTS customer_address;

CREATE TABLE customer_address(
  ADDRESS_CUSTOMER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  ADDRESS_ID BIGINT,
  CUSTOMER_ID BIGINT,
  FOREIGN KEY (ADDRESS_ID) REFERENCES address(address_id),
  FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(customer_id));

DROP TABLE IF EXISTS order_of_goods;

CREATE TABLE order_of_goods(
  ORDER_OF_GOODS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  CUSTOMER_ID BIGINT,
  STATUS INTEGER,
  TOTAL_PRICE DOUBLE,
  FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(customer_id));

DROP TABLE IF EXISTS ordered_item;

CREATE TABLE ordered_item(
  ORDERED_ITEM_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  PRODUCT_ID BIGINT,
  ORDER_OF_GOODS_ID BIGINT,
  QUANTITY INTEGER,
  PRICE_ALL_UNITS DOUBLE,
  FOREIGN KEY (ORDER_OF_GOODS_ID) REFERENCES order_of_goods(order_of_goods_id),
  FOREIGN KEY (PRODUCT_ID) REFERENCES product(product_id));

