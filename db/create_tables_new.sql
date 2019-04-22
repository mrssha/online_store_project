
DROP TABLE public."category" CASCADE;
DROP TABLE public."product" CASCADE;
DROP TABLE public."address" CASCADE;
DROP TABLE public."customer" CASCADE;
DROP TABLE public."order" CASCADE;
DROP TABLE public."basket" CASCADE;
DROP TABLE public."cart" CASCADE;


CREATE TABLE IF NOT EXISTS public."category"
(
    id_category integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    category_name character varying(45) UNIQUE NOT NULL,
    CONSTRAINT "category_pkey" PRIMARY KEY (id_category)
);


CREATE TABLE IF NOT EXISTS public."product"
(
    id_product integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_category integer NOT NULL,
    name character varying(45) UNIQUE NOT NULL,
    price integer NOT NULL,
    brand character varying(45) NOT NULL,
    weight double precision,
    sex character varying(6),
    quantity integer NOT NULL,
    image_sm character varying(45),
    image_bg character varying(45),
    bought integer default 0,
    CONSTRAINT "product_pkey" PRIMARY KEY (id_product),
    CONSTRAINT fk_product_category FOREIGN KEY (id_category)
        REFERENCES public."category" (id_category)
);


CREATE TABLE IF NOT EXISTS public."customer"
(
    id_customer integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    first_name character varying(45) NOT NULL,
    second_name character varying(45) NOT NULL,
    email character varying(45) UNIQUE NOT NULL,
    password character varying(255) NOT NULL,
    birth_date date,
    role character varying(15) NOT NULL
        CHECK (role in ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_ANONYMOUS')),
    CONSTRAINT "customer_pkey" PRIMARY KEY (id_customer)
);


CREATE TABLE IF NOT EXISTS public."address"
(
    id_address integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_customer integer,
    country character varying(45),
    city character varying(45),
    postcode integer,
    street character varying(45),
    flat_number integer,
    house_number integer,
    address_type character varying(10) CHECK (address_type in ('CUSTOMER', 'PICKUP')),
    CONSTRAINT "address_pkey" PRIMARY KEY (id_address),
    CONSTRAINT fk_address_customer FOREIGN KEY (id_customer)
        REFERENCES public."customer" (id_customer)
);


CREATE TABLE IF NOT EXISTS public."order"
(
    id_order integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_customer integer NOT NULL,
    id_customer_address integer NOT NULL,
    date_order date NOT NULL,
    quantity_products integer,
    payment_amount double precision,
    payment_method character varying(4) CHECK (payment_method in ('CASH', 'CARD')),
    delivery_method character varying(13)
      CHECK (delivery_method in ('COURIER', 'POSTAMPT', 'SELF_DELIVERY')),
    payment_status character varying(7) CHECK (payment_status in ('WAITING', 'PAID')) DEFAULT 'WAITING',
    order_status character varying(13)
     CHECK (order_status in ('WAIT_PAYMENT','WAIT_SHIPMENT', 'SHIPPED', 'DELIVERED')) DEFAULT 'WAIT_PAYMENT',

    CONSTRAINT "order_pkey" PRIMARY KEY (id_order),
    CONSTRAINT fk_order_address FOREIGN KEY (id_customer_address)
        REFERENCES public."address" (id_address),
    CONSTRAINT fk_order_customer FOREIGN KEY (id_customer)
        REFERENCES public."customer" (id_customer)
);



CREATE TABLE IF NOT EXISTS public."cart"
(
    id_cart integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_customer integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    unique (id_customer, id_product),
    CONSTRAINT "card_pkey" PRIMARY KEY (id_cart),
    CONSTRAINT fk_card_customer FOREIGN KEY (id_customer)
        REFERENCES public."customer" (id_customer),
    CONSTRAINT fk_card_product FOREIGN KEY (id_product)
        REFERENCES public."product" (id_product)
);


CREATE TABLE IF NOT EXISTS public."basket"
(
    id_basket integer GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_order integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    unique (id_order, id_product),
    CONSTRAINT "basket_pkey" PRIMARY KEY (id_basket),
    CONSTRAINT fk_basket_order FOREIGN KEY (id_order)
        REFERENCES public."order" (id_order),
    CONSTRAINT fk_basket_product FOREIGN KEY (id_product)
        REFERENCES public."product" (id_product)

);


/*
CREATE TABLE IF NOT EXISTS public."basket"
(
    id_order integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT "basket_pkey" PRIMARY KEY (id_order, id_product),
    CONSTRAINT fk_basket_order FOREIGN KEY (id_order)
        REFERENCES public."order" (id_order),
    CONSTRAINT fk_basket_product FOREIGN KEY (id_product)
        REFERENCES public."product" (id_product)
);
*/
