create table client(
    email varchar(255) primary key,
    user_name varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    subscription varchar(20) NOT NULL,
    subscription_ends date NOT NULL
);

create table exercise(
    name varchar(255) primary key,
    muscleGroup varchar(255),
    DetailedMuscleGroup varchar(255),
    OtherMuscleGroup varchar(255),
    type varchar(255),
    mechanics varchar(255),
    equipment varchar(255),
    difficulty varchar(255),
    description varchar,
    gif varchar
);


--create table exercises(
--    id bigserial(255) primary key,
--    difficulty varchar(255),
--    equipment varchar(255),
--    muscle_group varchar(255),
--    type varchar(255),
--);
