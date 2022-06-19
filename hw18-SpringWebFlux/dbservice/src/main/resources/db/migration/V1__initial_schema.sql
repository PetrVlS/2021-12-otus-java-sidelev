
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

insert into clients values (1, 'Вася');
insert into addresses values (1, 'ул. Лесной проспект 16', 1);
insert into phones values (1, '8(999) 665-71-98', 1);
insert into phones values (2, '8(923) 659-98-26', 1);

insert into clients values (2, 'Иван');
insert into addresses values (2, 'ул. Набережная 19', 2);
insert into phones values (3, '8(999) 574-23-96', 2);
insert into phones values (4, '8(913) 213-62-47', 2);




