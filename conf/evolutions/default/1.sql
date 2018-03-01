# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customer (
  customer_id               bigint auto_increment not null,
  title                     varchar(255),
  name                      varchar(255),
  dob                       datetime(6),
  salary                    double,
  city                      varchar(255),
  province                  varchar(255),
  address                   varchar(255),
  postal_code               varchar(255),
  constraint pk_customer primary key (customer_id))
;

create table item (
  item_code                 bigint auto_increment not null,
  description               varchar(255),
  unit_price                double,
  qty_on_hand               integer,
  constraint pk_item primary key (item_code))
;

create table orderDetails (
  order_detail_id           bigint auto_increment not null,
  orders_order_id           bigint,
  item_item_code            bigint,
  order_qty                 integer,
  discount                  double,
  constraint pk_orderDetails primary key (order_detail_id))
;

create table orders (
  order_id                  bigint auto_increment not null,
  date                      datetime(6),
  customer_customer_id      bigint,
  user_user_id              bigint,
  constraint pk_orders primary key (order_id))
;

create table user (
  user_id                   bigint auto_increment not null,
  user_name                 varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (user_id))
;

alter table orderDetails add constraint fk_orderDetails_orders_1 foreign key (orders_order_id) references orders (order_id) on delete restrict on update restrict;
create index ix_orderDetails_orders_1 on orderDetails (orders_order_id);
alter table orderDetails add constraint fk_orderDetails_item_2 foreign key (item_item_code) references item (item_code) on delete restrict on update restrict;
create index ix_orderDetails_item_2 on orderDetails (item_item_code);
alter table orders add constraint fk_orders_customer_3 foreign key (customer_customer_id) references customer (customer_id) on delete restrict on update restrict;
create index ix_orders_customer_3 on orders (customer_customer_id);
alter table orders add constraint fk_orders_user_4 foreign key (user_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_orders_user_4 on orders (user_user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table customer;

drop table item;

drop table orderDetails;

drop table orders;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

