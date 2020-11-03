
CREATE TABLE ROLE (
	id_role int not null auto_increment,
    role varchar(45),
    primary key (id_role)
);

CREATE TABLE users (
	id_user int not null auto_increment,
    user_name varchar(45),
    ppassword varchar(12),
    email varchar(30),
    phone_number varchar(15),
    role_id int,
    primary key(id_user),
    foreign key (role_id) references role(id_role)
);

create table operators (
	id_operator int not null auto_increment,
    first_name varchar(25),
    last_name varchar(25),
    phone_number varchar(15),
    email varchar(30),
    user_id int unique,
    primary key(id_operator),
    foreign key (user_id) references users(id_user)
);

create table supplier (
	id_supplier int not null auto_increment,
    first_name varchar(25),
    last_name varchar(25),
    phone_number varchar(15),
    email varchar(30),
    company varchar(30),
    adress varchar(45),
    primary key(id_supplier)
);

create table clients (
	id_clients int not null auto_increment,
    first_name varchar(25),
    last_name varchar(25),
    phone_number varchar(15),
    email varchar(30),
    company varchar(30),
    adress varchar(45),
    primary key(id_clients)
);

create table delivery_notes (
	id_delivery int not null auto_increment,
    ddate date,
    supplier_id int,
    operator_id int,
    primary key (id_delivery),
    foreign key (supplier_id) references supplier(id_supplier),
    foreign key (operator_id) references operators(id_operator)
);

create table invoice (
	id_invoice int not null auto_increment,
    ddate date,
    clients_id int,
    operator_id int,
    primary key (id_invoice),
    foreign key (clients_id) references clients(id_clients),
    foreign key (operator_id) references operators(id_operator)
);

create table brands (
	id_brand int not null auto_increment,
    brand varchar(30),
    primary key(id_brand)
);

create table type (
	id_type int not null auto_increment,
    type varchar(30),
    primary key(id_type)
);

create table stocks(
	id_stock int not null auto_increment,
    amount double,
    brand_id int,
    type_id int,
    primary key(id_stock),
    foreign key(brand_id) references brands(id_brand),
    foreign key(type_id) references type(id_type)
);

create table delivery_note_items(
	id int not null auto_increment,
    single_price double,
    amount_delivery double,
    stock_id int,
    delivery_note_id int,
    primary key(id),
    foreign key(stock_id) references stocks(id_stock),
    foreign key(delivery_note_id) references delivery_notes(id_delivery)
);


create table invoice_items(
	id int not null auto_increment,
    single_price double,
    amount_delivery double,
    stock_id int,
    invoice_id int,
    primary key(id),
    foreign key(stock_id) references stocks(id_stock),
    foreign key(invoice_id) references invoice(id_invoice)
);

create table cash_register (
	id int not null auto_increment,
    money double,
    primary key(id)
);








    
    
    


