create table if not exists ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists taco (
  id int not null AUTO_INCREMENT,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists taco_ingredients (
  taco_id bigint not null,
  ingredient_id varchar(4) not null
);

alter table taco_ingredients add foreign key (taco_id) references taco(id);
alter table taco_ingredients add foreign key (ingredient_id) references ingredient(id);

create table if not exists taco_order (
  id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists taco_order_tacos (
  tacoOrder bigint not null,
  taco bigint not null
);

alter table taco_order_tacos add foreign key (tacoOrder) references taco_order(id);
alter table taco_order_tacos add foreign key (taco) references taco(id);