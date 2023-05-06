SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `servify_db` DEFAULT CHARACTER SET utf8 ;
USE `servify_db` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS `details`
(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `side` VARCHAR(100) NOT NULL,
  `price` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `details`
ADD `color` VARCHAR(100) NOT NULL;

CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` INT NOT NULL,
  `user_type` VARCHAR(100) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  `is_inactive` BOOLEAN NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_date` DATETIME NOT NULL,
  `total_price` DECIMAL(10, 2),
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;

ALTER TABLE `orders`
ADD `order_close_date` DATETIME;

ALTER TABLE `orders`
ADD FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `details`
ADD FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`);