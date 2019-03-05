-- Table: public."product"

--DROP TABLE public."product" CASCADE;
--DROP TABLE public."address" CASCADE;
--DROP TABLE public."customer" CASCADE;
--DROP TABLE public."order" CASCADE;
--DROP TABLE public."basket" CASCADE;


CREATE SEQUENCE IF NOT EXISTS public.Products_id_seq;

--CREATE TYPE payment_status_enum AS ENUM ('wait_for_payment', 'paid');
--CREATE TYPE order_status_enum AS ENUM ('wait_for_payment','wait_for_shipment', 'shipped', 'delivered');
--CREATE TYPE payment_method_enum AS ENUM ('by_cash', 'by_card');
--CREATE TYPE delivery_method_enum AS ENUM ('courier', 'postampt', 'self_delivery');


CREATE TABLE IF NOT EXISTS public."product"
(
    id_product serial NOT NULL,
    name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    price double precision NOT NULL,
    category character varying(45) COLLATE pg_catalog."default" NOT NULL,
    brand character varying(45) COLLATE pg_catalog."default" NOT NULL,
    weigth double precision,
    sex character varying(6) COLLATE pg_catalog."default",
    quantity integer NOT NULL,
    CONSTRAINT "product_pkey" PRIMARY KEY (id_product)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public."address"
(
    id_address SERIAL NOT NULL,
    country character varying(45) COLLATE pg_catalog."default",
    city character varying(45) COLLATE pg_catalog."default",
    postcode integer,
    street character varying(45) COLLATE pg_catalog."default",
    flat_number integer,
    house_number integer,
    CONSTRAINT "address_pkey" PRIMARY KEY (id_address)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;



CREATE TABLE IF NOT EXISTS public."customer"
(
    id_customer SERIAL NOT NULL,
    first_name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    second_name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    email character varying(45) COLLATE pg_catalog."default" NOT NULL,
    password character varying(45) COLLATE pg_catalog."default" NOT NULL,
    address integer NOT NULL,
    birth_date date,
    CONSTRAINT "customer_pkey" PRIMARY KEY (id_customer),
    CONSTRAINT fk_address FOREIGN KEY (address)
        REFERENCES public."address" (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public."order"
(
    id_order SERIAL NOT NULL,
    id_customer integer NOT NULL,
    id_customer_address integer,
    payment_method payment_method_enum,
    delivery_method delivery_method_enum,
    all_purchase integer[],
    quantity_of_purchases integer,
    sum_value double precision,
    status_of_payment payment_status_enum,
    status_of_order order_status_enum,
    CONSTRAINT "order_pkey" PRIMARY KEY (id_order),
    CONSTRAINT fk_order_address FOREIGN KEY (id_customer_address)
        REFERENCES public."address" (id_address) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_customer FOREIGN KEY (id_customer)
        REFERENCES public."customer" (id_customer) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public."basket"
(
    id_order integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT "basket_pkey" PRIMARY KEY (id_order, id_product),
    CONSTRAINT fk_basket_order FOREIGN KEY (id_order)
        REFERENCES public."order" (id_order) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_basket_product FOREIGN KEY (id_product)
        REFERENCES public."product" (id_product) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;



insert into "address" (country, city, postcode, street, flat_number, house_number)
values ('Russia', 'NN', 123456, 'street', 1, 2);

insert into "customer" (first_name, second_name, email, password, address, birth_date)
values ('Maria', 'Shalnova', 'mail@mail.ru', '123ddd', 1, '1999-01-08');


insert into "product" (name, price, category, brand, weigth, sex, quantity)
values ('Snowboard K2', 15000,'snowboards', 'K2', 300, 'unisex', 10);

insert into "product" (name, price, category, brand, weigth, sex, quantity)
values ('Snowboard 666', 10000,'snowboards', '666', 400, 'unisex', 3);

insert into "product" (name, price, category, brand, weigth, sex, quantity)
values ('Snowboard glasses', 5000,'accessories', 'K2', 100, 'unisex', 5);

insert into "product" (name, price, category, brand, weigth, sex, quantity)
values ('Snowboard coat', 5000,'clothes', '666', 300, 'male', 5);

insert into "product" (name, price, category, brand, weigth, sex, quantity)
values ('Snowboard coat', 5000,'clothes', '666', 300, 'female', 5);


insert into "order" (id_customer, id_customer_address) values (1, 1);
insert into "order" (id_customer, id_customer_address) values (2, 2);


insert into "basket"  (id_order, id_product, quantity) VALUES (1, 2, 1);
insert into "basket"  (id_order, id_product, quantity) VALUES (1, 5, 1);
insert into "basket"  (id_order, id_product, quantity) VALUES (1, 3, 2);




insert into "address" (country, city, postcode, street, flat_number, house_number)
values ('Russia', 'NN', 123457, 'street', 2, 3);

insert into "customer" (first_name, second_name, email, password, address, birth_date)
values ('Sofia', 'Ivanova', 'mail@mail.ru', '123ddd', 2, '1999-01-08');


insert into "basket"  (id_order, id_product, quantity) VALUES (2, 2, 2);
insert into "basket"  (id_order, id_product, quantity) VALUES (2, 5, 3);
insert into "basket"  (id_order, id_product, quantity) VALUES (1, 1, 1);



select "product".id_product, "product".price, "basket".quantity
from "order" join "basket"
ON "order".id_order = "basket".id_order
join "product"
ON "product".id_product = "basket".id_product
where "order".id_order =1;


select sum(price*quantity) as sum_purchase, sum(quantity) as quantity_purchase from
(select "product".id_product, "product".price, "basket".quantity
from "order" join "basket"
ON "order".id_order = "basket".id_order
join "product"
ON "product".id_product = "basket".id_product
where "order".id_order =1) as customers_purchase;



/*
insert into "Orders"
(id_customer, id_customer_address, payment_method, delivery_method, all_purchase,
 quantity_of_purchases, sum_value, status_of_payment, status_of_order)
 values
 (1, 1, 'by_cash', 'courier', null, 25000 , 4,
  'wait_for_payment', 'wait_for_payment')
*/

