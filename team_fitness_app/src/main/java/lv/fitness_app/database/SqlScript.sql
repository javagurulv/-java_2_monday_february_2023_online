create schema user_db;

create table user_db.user(
    email varchar(255) primary key,
    user_name varchar(255),
    password varchar(255),
    subscription varchar(25),
    subscription_ends date
);

create schema exercices_db;

create table exercises_db.exercises(
    id bigserial(255) primary key,
    difficulty varchar(255),
    equipment varchar(255),
    muscle_group varchar(255),
    type varchar(255),
);
