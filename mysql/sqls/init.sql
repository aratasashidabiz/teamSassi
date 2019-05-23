drop database if exists mbshop;
create user mbshop;
set password for mbshop = 'himitu';
create database mbshop;
grant all on mbshop.* to mbshop;

