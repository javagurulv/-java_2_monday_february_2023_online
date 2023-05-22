INSERT INTO details_type (`id`, `detail_type`)
VALUES
(1, "BONNET"),
(2, "BOOT"),
(3, "ROOF"),
(4, "BUMPER"),
(5, "DOOR"),
(6, "WING"),
(7, "MIRROR");

INSERT INTO details_side (`id`, `detail_side`)
VALUES
(1, "FRONT"),
(2, "REAR"),
(3, "LEFT"),
(4, "RIGHT"),
(5, "FRON_LEFT"),
(6, "FRONT_RIGHT"),
(7, "REAR_LEFT"),
(8, "REAR_RIGHT"),
(9, "NO_SIDE");

insert into details(`detail_type_id`, `detail_side_id`, `price`)
VALUES
(1, 9, 200),
(2, 9, 180),
(3, 9, 250),
(4, 1, 180),
(4, 2, 150),
(5, 5, 180),
(5, 6, 180),
(5, 7, 180),
(5, 8, 180),
(6, 5, 130),
(6, 6, 130),
(6, 7, 160),
(6, 8, 160),
(7, 3, 60),
(7, 4, 60);

INSERT INTO colors (`color_code`, `color_name`, `is_metalic`)
VALUES
(601, "black", FALSE),
(651, "black", TRUE);

INSERT INTO orders_status (`order_status`)
VALUES
("NEW"),
("IN_PROGRESS"),
("SUBMITTED_BY_CLIENT"),
("REFERED_TO_MANAGER"),
("MANAGER_PRICE_ADJUSTED"),
("FINAL_PRICE_CALCULATED"),
("PROPOSED_TO_CUSTOMER"),
("DECLINED_BY_CUSTOMER"),
("DECLINED_BY_MANAGER"),
("APPROVED_BY_MANAGER"),
("APPROVED_BY_CUSTOMER");

INSERT INTO users_types (`user_types`)
VALUES
("ANONYMOUS"),
("CUSTOMER"),
("MANAGER");

INSERT INTO address (`country`, `city`, `street`, `house_number`, `apartment_number`)
VALUES
("Latvia", "Riga", "Deglava", "151 A", "401");

INSERT INTO users (`first_name`, `last_name`, `email`, `phone_number`, `is_inactive`, `password`, `user_type_id`, `address_id`)
VALUES
("Alex", "Lee", "myemail@gmail.com", "28555777",  false, "777555", 1, 1),
("John", "Dou", "joundou@gmail.com", "28999888",  false, "777", 2, null),
("Iam", "Boss", "iamboss@gmail.com", "28777777",  false, "000", 3, null);

INSERT INTO orders (`user_id`, `order_date`, `order_close_date`, `total_price`)
VALUES
(1, "2023-12-15 10:35:00", null, 260),
(2, "2023-12-15 12:00:00", NULL, 630);

INSERT INTO orders_items (`order_id`, `detail_id`, `color_id`, `cunt`)
VALUES
(1, 1, 1, 1),
(1, 7, 1, 1),
(2, 1, 2, 1),
(2, 2, 2, 1),
(2, 3, 2, 1);