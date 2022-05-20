
create table clients
(
    id   bigserial not null primary key,
    name varchar(50)
);
create table addresses
(
    id   bigserial not null primary key,
    street varchar(50),
    client_id bigint references clients(id)
);
create table phones
(
    id   bigserial not null primary key,
    number varchar(50),
    client_id bigint references clients(id)
);


