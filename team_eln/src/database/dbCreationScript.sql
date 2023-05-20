SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2ELN` DEFAULT CHARACTER SET utf8 ;
USE `java2ELN`;

CREATE TABLE `ReactionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `structure_mainProduct_id` int,
  `reactionYield` double,
  `user_id` int
);

CREATE TABLE `ReactionStartingMaterial` (
  `reaction_id` int,
  `structure_id` int,
  PRIMARY KEY (`reaction_id`, `structure_id`)
);

CREATE TABLE `ConditionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `structure_solvent_id` int,
  `temperature` int,
  `environment` varchar(255),
  `pressure` int,
  `reactionTime` time,
  `reaction_id` int
);

CREATE TABLE `StructureData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `smiles` varchar(255),
  `casNumber` varchar(255),
  `name` varchar(255),
  `internalCode` varchar(255),
  `mass` double
);

CREATE TABLE `user` (
  `user_id` int PRIMARY KEY,
  `first_name` varchar(255),
  `last_name` varchar(255),
);

ALTER TABLE `ConditionData` ADD FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionData` (`structure_mainProduct_id`);

ALTER TABLE `ReactionData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`reaction_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`structure_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ConditionData` (`structure_solvent_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`user_id`) REFERENCES `ReactionData` (`user_id`);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

