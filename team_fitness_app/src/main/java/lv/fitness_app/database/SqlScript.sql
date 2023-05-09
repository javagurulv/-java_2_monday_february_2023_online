create schema user_db;

create table user_db.user(
    email varchar(255) primary key,
    user_name varchar(255),
    password varchar(255),
    subscription varchar(25),
    subscription_ends date
);