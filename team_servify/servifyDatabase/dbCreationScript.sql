SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `servify_db` DEFAULT CHARACTER SET utf8 ;
USE `servify_db` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS `colors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `color_code` VARCHAR(100) NOT NULL,
  `color_name` VARCHAR(500) NOT NULL,
  `is_metalic` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `details_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `detail_type` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `details_side` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `detail_side` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orders_status` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_status` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users_types` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_types` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `details`
(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `detail_type_id` BIGINT NOT NULL,
  `detail_side_id` BIGINT NOT NULL,
  `price` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`detail_type_id`) REFERENCES `details_type`(`id`),
  FOREIGN KEY (`detail_side_id`) REFERENCES `details_side`(`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(500) NOT NULL,
  `city` VARCHAR(500) NOT NULL,
  `street` VARCHAR(500) NOT NULL,
  `house_number` VARCHAR(100) NOT NULL,
  `apartment_number` VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` INT NOT NULL,
  `user_type_id` BIGINT NOT NULL,
  `address_id` bigint,
  `is_inactive` BOOLEAN NOT NULL,
  `password` VARCHAR(500),
  FOREIGN KEY (`user_type_id`) REFERENCES `users_types`(`id`),
  FOREIGN KEY (`address_id`) REFERENCES `address`(`id`),
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `order_close_date` DATETIME,
  `total_price` DECIMAL(10, 2),
  PRIMARY KEY (id),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orders_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `detail_id` BIGINT NOT NULL,
  `color_id` BIGINT NOT NULL,
  `cunt` BIGINT NOT NULL,
  FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
  FOREIGN KEY (`detail_id`) REFERENCES `details`(`id`),
  FOREIGN KEY (`color_id`) REFERENCES `colors`(`id`),
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

ALTER TABLE `users`
ALTER COLUMN `is_inactive` SET DEFAULT FALSE;