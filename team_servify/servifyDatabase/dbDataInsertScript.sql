insert into details(`detail_type`, `detail_side`, `price`)
VALUES
("BONNET", "NO_SIDE", 200),
("BOOT", "NO_SIDE", 180),
("ROOF", "NO_SIDE", 250),
("BUMPER", "FRONT", 180),
("BUMPER", "REAR", 150),
("DOOR", "FRON_LEFT", 180),
("DOOR", "FRONT_RIGHT", 180),
("DOOR", "REAR_LEFT", 180),
("DOOR", "REAR_RIGHT", 180),
("WING", "FRON_LEFT", 130),
("WING", "FRONT_RIGHT", 130),
("WING", "REAR_LEFT", 160),
("WING", "REAR_RIGHT", 160),
("MIRROR", "LEFT", 60),
("MIRROR", "RIGHT", 60);

INSERT INTO colors (`color_code`, `color_name`, `is_metalic`)
VALUES
(601, "black", FALSE),
(651, "black", TRUE);

INSERT INTO addresses (`country`, `city`, `street`, `house_number`, `apartment_number`, `postal_code`)
VALUES
("Latvia", "Riga", "Deglava", "151 A", "401", "LV-1082"),
("Latvia", "Riga", "Dzelzavas", "25", "12", "LV-1082");

INSERT INTO roles (`role`)
VALUES
( "ANONYMOUS"),
( "CUSTOMER"),
( "EMPLOYEE"),
( "MANAGER");


INSERT INTO users (`first_name`, `last_name`, `email`, `phone_number`, `active`, `password`, `user_name`)
VALUES
("Alex", "Lee", "myemail@gmail.com", "28555777",  true, "$2a$12$yRlsy8AdTxyu6TdskFRqkOAzvSTIKXewt/o/DaQflZQG3KDoIb.D.", "myemail@gmail.com"),
("John", "Dou", "joundou@gmail.com", "28999888",  true, "$2a$12$ZBImhFfkfbAHGQwKRtX8G.EO6nWbyHBfnM11OxPzP7ZacOPJYaFmG", "joundou@gmail.com"),
("Janis", "Ozols", "j_ozols@gmail.com", "28111111",  true, "$2a$12$14FjqRFXiALspL0NqGBHCuiTNE2boc5HNxfbWebq9olApJDaJKs8.", "j_ozols@gmail.com"),
("Iam", "Boss", "iamboss@gmail.com", "28777777",  true, "$2a$12$EH/5Sybsh6LYVRwgXwVT7.hP0A36c7Pfa8SZzdMp9gjnzTzh4Qc8i", "iamboss@gmail.com");

INSERT INTO orders (`user_id`, `order_date`, `order_close_date`, `order_status`, `total_price`)
VALUES
(1, "2023-12-15 10:35:00", "2023-12-20 10:00:00", "COMPLETE", 260),
(2, "2023-12-15 12:00:00", NULL, "IN_PROGRESS", 630),
(1, "2023-12-15 10:35:00", null, "NEW", 330);

INSERT INTO orders_items (`order_id`, `detail_id`, `color_id`, `count`)
VALUES
(1, 1, 1, 1),
(1, 7, 1, 1),
(2, 1, 2, 1),
(2, 2, 2, 1),
(2, 3, 2, 1),
(3, 4, 2, 1),
(3, 5, 2, 1);

INSERT INTO user_role (`user_id`, `role_id`)
VALUES
(1, 2),
(2, 2),
(3, 3),
(4, 4);
INSERT INTO user_address (`user_id`, `address_id`)
VALUES
(1, 1),
(2, 2),
(3, null),
(4, null);