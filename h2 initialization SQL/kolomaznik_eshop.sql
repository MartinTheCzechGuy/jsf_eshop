DROP TABLE IF EXISTS product;
CREATE TABLE product (
  PRODUCT_ID BIGINT PRIMARY KEY,
  NAME VARCHAR(255),
  PRICE DOUBLE,
  IMG VARCHAR(255),
  DESCRIPTION VARCHAR(2000),
  UNITS_IN_STOCK INTEGER);

INSERT INTO product
  VALUES(1, 'PowerVision PowerRay Angler', 51000, 'PowerVisionAngler.jpeg', 'Dron podvodní, 4K kamera, vodotěsný do 30m, váha 3.8kg, v balení PoweRay, ovladač, nabíjecí stanice, PowerSekker Fishfinder, Bait Drop Line', 50);

INSERT INTO product
  VALUES(2, 'Power Vision PowerRay Wizard', 39900, 'PowerVisionWizard.jpeg', 'Dron podvodní, 4K kamera, vodotěsný do 30m, váha 3.8kg, v balení PoweRay, ovladač, nabíjecí stanice, PowerSeeker Fishfinder, Bait Drop Line, VR brýle', 50);

INSERT INTO product
  VALUES(3, 'DJI Mavic 2 Pro', 38900, 'Mavic2Pro.jpeg', 'Dron - 4K kamera Hasselblad s 3-osou stabilizací, snímač 1", 2x optický zoom, dosah až 8km, výdrž přibližně 31min, max. rychlost 72km/hod, 8GB vnitřní paměť, senzory pro detekci překážek, přistávací světlo, Activetrack', 100);

INSERT INTO product
  VALUES(4, 'DJI Phantom 4 Pro+ Obsidian Edition + DJI Goggles', 63000,'DJIPantom.jpeg', 'Dron - 4K kamera s 3-osou stabilizací 1 snímačem, dosah až 7km, výdrž přibližně 29min, max. rychlost 20m/s, hmotnost 1388g, GPS, microSD (max.128GB), magnesiový skelet, antikolizní čidla, funkce Tapfly a ActiveTrack, dálkové ovládání s displejem, brýle DJI Goggles', 44);

INSERT INTO product
  VALUES(5, 'RYZE Tello Boost Combo', 5000, 'TelloBoost.jpeg', 'Dron - micro selfie dron, vybaven vestavěnou HD kamerou s rozlišením videa 720/30p, 80g, přenos na vzdálenost 100m, varianta v Combo Boost balení', 120);

INSERT INTO product
  VALUES(6, 'Xiaomi Mi Drone Mini', 1300, 'xiaomi.jpeg', 'Dron s pokročilými funkcemi, Headless Mode, G-senzor, HD kamera, gyroskop, akcelerometr, optické snímače, funkce Altitude Hold, infračervený režim bitvy, dosah až 50m, délka letu 7-10 minut, spárování s aplikací v telefonu', 54);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  CUSTOMER_ID BIGINT PRIMARY KEY,
  FIRST_NAME VARCHAR(100),
  LAST_NAME VARCHAR(100),
  EMAIL VARCHAR(100),
  TELEPHONE INTEGER);

DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card(
  CREDIT_CARD_ID BIGINT PRIMARY KEY,
  NUMBER VARCHAR(50),
  VALIDITY VARCHAR(30),
  CUSTOMER_ID BIGINT,
  FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID));

DROP TABLE IF EXISTS address;

CREATE TABLE address(
  ADDRESS_ID BIGINT PRIMARY KEY,
  COUNTRY VARCHAR(50),
  CITY VARCHAR(50),
  STREET VARCHAR(50),
  HOUSE_NUMBER INT,
  ZIP INT);

DROP TABLE IF EXISTS customer_address;

CREATE TABLE customer_address(
  ADDRESS_CUSTOMER_ID BIGINT PRIMARY KEY,
  ADDRESS_ID BIGINT,
  CUSTOMER_ID BIGINT,
  FOREIGN KEY (ADDRESS_ID) REFERENCES address(address_id),
  FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(customer_id));

DROP TABLE IF EXISTS order_of_goods;

CREATE TABLE order_of_goods(
  ORDER_OF_GOODS_ID BIGINT PRIMARY KEY,
  CUSTOMER_ID BIGINT,
  STATUS INTEGER,
  TOTAL_PRICE DOUBLE,
  FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(customer_id));

DROP TABLE IF EXISTS ordered_item;

CREATE TABLE ordered_item(
  ORDERED_ITEM_ID BIGINT PRIMARY KEY,
  PRODUCT_ID BIGINT,
  QUANTITY INTEGER,
  PRICE DOUBLE,
  FOREIGN KEY (PRODUCT_ID) REFERENCES product(product_id));

DROP TABLE IF EXISTS ORDEROFGOODS_ORDEREDITEM;

CREATE TABLE ORDEROFGOODS_ORDEREDITEM(
  ORDEROFGOODS_ORDEREDITEM_ID BIGINT PRIMARY KEY,
  ORDER_OF_GOODS_ID BIGINT,
  ORDERED_ITEM_ID BIGINT,
  FOREIGN KEY (ORDER_OF_GOODS_ID) REFERENCES ORDER_OF_GOODS(ORDER_OF_GOODS_ID),
  FOREIGN KEY (ORDERED_ITEM_ID) REFERENCES ordered_item(ordered_item_id));
