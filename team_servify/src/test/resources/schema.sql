DROP TABLE IF EXISTS orders_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS details CASCADE;
DROP TABLE IF EXISTS colors CASCADE;

CREATE TABLE IF NOT EXISTS `colors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `color_code` VARCHAR(100) NOT NULL,
  `color_name` VARCHAR(500) NOT NULL,
  `is_metalic` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `details`
(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `detail_type` VARCHAR(100) NOT NULL,
  `detail_side` VARCHAR(100),
  `price` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(500) NOT NULL,
  `city` VARCHAR(500) NOT NULL,
  `street` VARCHAR(500) NOT NULL,
  `house_number` VARCHAR(100) NOT NULL,
  `apartment_number` VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(100) NOT NULL,
  `user_type` VARCHAR(100) NOT NULL,
  `address_id` bigint,
  `is_inactive` BOOLEAN NOT NULL,
  `password` VARCHAR(500),
  FOREIGN KEY (`address_id`) REFERENCES `address`(`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_status` VARCHAR(100) NOT NULL,
  `order_date` DATETIME NOT NULL,
  `order_close_date` DATETIME,
  `total_price` DECIMAL(10, 2),
  PRIMARY KEY (id),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE IF NOT EXISTS `orders_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `detail_id` BIGINT NOT NULL,
  `color_id` BIGINT NOT NULL,
  `count` BIGINT NOT NULL,
  FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
  FOREIGN KEY (`detail_id`) REFERENCES `details`(`id`),
  FOREIGN KEY (`color_id`) REFERENCES `colors`(`id`),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `users`
ALTER COLUMN `is_inactive` SET DEFAULT FALSE;

ALTER TABLE `address`
ADD COLUMN `postal_code`VARCHAR(500);

ALTER TABLE `orders`
ADD COLUMN `notes` VARCHAR(2000);
