insert into details(`id`, `type`, `side`, `price`)
values (1,"BONNET", "NO_SIDE", 200);
insert into details(`type`, `side`, `price`)
values ("BOOT", "NO_SIDE", 180);
insert into details(`type`, `side`, `price`)
values ("ROOF", "NO_SIDE", 250);
insert into details(`type`, `side`, `price`)
values ("BUMPER", "FRONT", 180);
insert into details(`type`, `side`, `price`)
values ("BUMPER", "REAR", 150);
insert into details(`type`, `side`, `price`)
values ("DOOR", "FRONT_LEFT", 180);
insert into details(`type`, `side`, `price`)
values ("DOOR", "FRONT_RIGHT", 180);
insert into details(`type`, `side`, `price`)
values ("DOOR", "REAR_LEFT", 180);
insert into details(`type`, `side`, `price`)
values ("DOOR", "REAR_RIGHT", 180);
insert into details(`type`, `side`, `price`)
values ("WING", "FRONT_LEFT", 130);
insert into details(`type`, `side`, `price`)
values ("WING", "FRONT_RIGHT", 130);
insert into details(`type`, `side`, `price`)
values ("WING", "REAR_LEFT", 160);
insert into details(`type`, `side`, `price`)
values ("WING", "REAR_RIGHT", 160);
insert into details(`type`, `side`, `price`)
values ("MIRROR", "LEFT", 60);
insert into details(`type`, `side`, `price`)
values ("MIRROR", "RIGHT", 60);

insert into users(`first_name`, `last_name`, `email`, `phone_number`, `user_type`, `is_inactive`, `password`)
values ("Alex", "Lee", "myemail@gmail.com", "28555777", "CUSTOMER", false, "777555");

insert into orders(user_id, detail_id, order_date, total_price)
values (1, 1, "2023-12-15 10:35:00", 260);

insert into orders(user_id, detail_id, order_date, total_price)
values (1, 15, "2023-12-15 10:35:00", 260);