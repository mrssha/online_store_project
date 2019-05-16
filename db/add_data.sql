
insert into "customer"(first_name, second_name, email, password, birth_date, role)
values ('Admin', 'Admin', 'admin@mail.ru',
        '$2a$10$aoaPysMjRLQppdJEMDCtTevDyKwKcLHiW0TtF5kT4dxU6aSNFrxdG','1995-04-01', 'ROLE_ADMIN');

insert into "customer"(first_name, second_name, email, password, birth_date, role, sum_purchases)
values ('Anton', 'Pavlov', 'pavlov@mail.ru',
        '$2a$10$aoaPysMjRLQppdJEMDCtTevDyKwKcLHiW0TtF5kT4dxU6aSNFrxdG','1990-04-01', 'ROLE_USER', 5000.0);

insert into "customer"(first_name, second_name, email, password, birth_date, role, sum_purchases)
values ('Sony', 'R', 'sony@mail.ru',
        '$2a$10$aoaPysMjRLQppdJEMDCtTevDyKwKcLHiW0TtF5kT4dxU6aSNFrxdG','1991-04-01', 'ROLE_USER', 25000.55);

insert into "customer"(first_name, second_name, email, password, birth_date, role, sum_purchases)
values ('Lol', 'Lol', 'lol@mail.ru',
        '$2a$10$aoaPysMjRLQppdJEMDCtTevDyKwKcLHiW0TtF5kT4dxU6aSNFrxdG','1992-04-01', 'ROLE_USER', 100000.1);


insert into "address" ( country, city, postcode, street, flat_number, house_number, active, address_type)
values ('Russia', 'NN', 123456, '1 street', 1, 2, true, 'PICKUP');
insert into "address" (country, city, postcode, street, flat_number, house_number, active, address_type)
values ('Russia', 'SPB', 555555, '2 street', 10, 20, true, 'PICKUP');

insert into "category"(category_name) values ('snowboards');
insert into "category"(category_name) values ('snowboard boots');
insert into "category"(category_name) values ('ski');

-- insert into "product" (name, price, id_category, brand, weight, sex, quantity)
-- values ('Snowboard K2', 15000, 1 , 'K2', 300, 'unisex', 10);
-- insert into "product" (name, price, id_category, brand, weight, sex, quantity)
-- values ('Snowboard 666', 10000, 1 , '666', 400, 'unisex', 3);



--Сноуборды с картинками
insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Special Lady 2017-18 lipstick', 8500, 1 , 'BF', 300, 'unisex', 10,'1_BF.jpg','1_BF_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('BURTON AFTER SCHOOL SPE 2018-19', 10100, 1, 'BURTON', 300, 'unisex', 10,'3_Burton.jpg','3_Burton_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Jones Prodigy 2018-19', 18400, 1, 'Jones', 500, 'unisex', 20,'2_Jones.jpg','2_jones_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('NIDECKER Elle 2018-19', 11200, 1, 'NIDECKER', 600, 'unisex', 20,'4_nid.jpg','4_nid_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Roxy SMOOTHIE C2 2018-19', 26500, 1, 'ROXY', 1000, 'unisex', 15,'5_roxy.jpg','5_roxy_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Roxy ALLY BAN 2018-19', 25200, 1, 'ROXY', 700, 'unisex', 8,'6_roxy.jpg','6_roxy_big.jpg');


insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Roxy SUGAR BAN 2018-19', 24500, 1, 'ROXY', 700, 'unisex', 10,'7_roxy.jpg','7_roxy_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('BURTON YEASAYER SMALLS 2018-19', 15500, 1, 'BURTON', 700, 'unisex',7,'8_burt.jpg','8_burt_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Jones Solution 2018-19', 52000, 1, 'Jones', 700, 'unisex',2,'10_jones.jpg','10_jones_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Roxy XOXO ban 2017-18', 26500, 1, 'ROXY', 800, 'unisex', 5,'11_roxy.jpg','11_roxy_big.jpg');


--Ботинки с картинками
insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Salomon 2018-19 PEARL Bordeaux11', 13000, 2, 'Salomon', 800, 'unisex', 10,
        '12_b_salomon_sm.jpg','12_b_salomon_big.jpg');


--Ski-
insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('BURTON 2018-19', 26500, 3, 'BURTON', 800, 'unisex', 15,'13_ski_sal_sm.jpg','13_ski_sal_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('Jones 2018-19 PROGRESSOR', 26500, 3, 'Jones', 800, 'unisex', 15,'14_ski_jones_sm.jpg','14_ski_jones_big.jpg');

insert into "product" (name, price, id_category, brand,weight, sex, quantity, image_sm, image_bg)
values ('NIDECKER 2018-19 STARR', 26500, 3, 'NIDECKER', 800, 'unisex', 15,'15_ski_nid_sm.jpg','15_ski_nid_bg.jpg');







UPDATE "product"
SET  quantity= 20 WHERE "product".id_product = 6;

UPDATE "product"
SET  name='Product!' WHERE "product".id_product = 20;

UPDATE "customer"
SET  sum_purchases= 0 WHERE "customer".id_customer = 2;

--delete from "product" where id_product=1;

-- insert into "order" (id_customer, id_customer_address, date_order) values (2, 1, '2018-02-01');
-- insert into "order" (id_customer, id_customer_address, date_order) values (1, 3, '2018-01-21');
-- insert into "order" (id_customer, id_customer_address, date_order) values (2, 4, '2018-02-02');

--insert into "order" (id_customer, date_order) values (2, '2018-02-01' );
--insert into "order" (id_customer, date_order ) values (1,'2018-02-01');
--insert into "order" (id_customer, date_order ) values (2,'2018-02-01' );

-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (1, 2, 1);
-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (1, 5, 1);
-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (1, 3, 2);
-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (2, 2, 2);
-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (2, 5, 3);
-- insert into "orderProduct"  (id_order, id_product, quantity) VALUES (1, 1, 1);


select * from "product" order by bought desc limit 10;

select * from "customer" order by sum_purchases desc limit 10;

select * from "order"
where date_order between '2019-04-01' and '2019-04-30';

select sum("order".payment_amount) from "order"
where date_order between '2019-04-01' and '2019-04-30';


select "product".id_product, "product".price, "orderProduct".quantity
from "order" join "orderProduct"
ON "order".id_order = "orderProduct".id_order
join "product"
ON "product".id_product = "orderProduct".id_product
where "order".id_order =1;


select sum(price*quantity) as sum_purchase, sum(quantity) as quantity_purchase from
(select "product".id_product, "product".price, "orderProduct".quantity
from "order" join "orderProduct"
ON "order".id_order = "orderProduct".id_order
join "product"
ON "product".id_product = "orderProduct".id_product
where "order".id_order =1) as customers_purchase;


/*
insert into "Orders"
(id_customer, id_customer_address, payment_method, delivery_method, all_purchase,
 quantity_of_purchases, sum_value, status_of_payment, status_of_order)
 values
 (1, 1, 'by_cash', 'courier', null, 25000 , 4,
  'wait_for_payment', 'wait_for_payment')
*/