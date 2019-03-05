

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