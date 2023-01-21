DROP TABLE IF EXISTS products CASCADE;

create table products
(
    id INTEGER IDENTITY PRIMARY KEY,
    name varchar(50) not null,
    price INTEGER not null
);
