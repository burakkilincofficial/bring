create table books
(
    id                 serial
        constraint books_pkey
            primary key,
    created_date       timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date timestamp,
    book_name          varchar(255),
    category           varchar(255),
    quantity_stock     integer,
    writer             varchar(255),
    unit_price         double precision
);

alter table books
    owner to postgres;

create table customers
(
    id                 serial
        constraint customers_pkey
            primary key,
    created_date       timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date timestamp,
    age                integer,
    name               varchar(255),
    surname            varchar(255),
    email              varchar(255)
);

alter table customers
    owner to postgres;

create table orders
(
    id                         serial
        constraint orders_pkey
            primary key,
    created_date               timestamp default CURRENT_TIMESTAMP not null,
    last_modified_date         timestamp,
    is_valid                   boolean   default true,
    customer_id                integer
        constraint fkpxtb8awmi0dk6smoh2vp1litg
            references customers,
    total_amount_all_purchased double precision
);

alter table orders
    owner to postgres;

create table order_books
(
    order_id integer not null
        constraint fk9ai8wib7jbokb4njnkwb8cy5i
            references orders,
    book_id  integer not null
        constraint fk7jqqp3k5449j9c6sllmyp263l
            references books
);

alter table order_books
    owner to postgres;

