SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2eln` DEFAULT CHARACTER SET utf8 ;
USE `java2eln` ;

CREATE TABLE IF NOT EXISTS `StructureData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `smiles` varchar(255),
  `casNumber` varchar(255),
  `name` varchar(255),
  `internalCode` varchar(255),
  `mass` double
);

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int PRIMARY KEY,
  `first_name` varchar(255),
  `last_name` varchar(255)
);

CREATE TABLE IF NOT EXISTS `ReactionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `structure_mainProduct_id` int,
  `reactionYield` double,
  `user_id` int,
  FOREIGN KEY (`structure_mainProduct_id`) REFERENCES `StructureData` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `ReactionStartingMaterial` (
  `reaction_id` int,
  `structure_id` int,
  PRIMARY KEY (`reaction_id`, `structure_id`),
  FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`),
  FOREIGN KEY (`structure_id`) REFERENCES `StructureData` (`id`)
);

CREATE TABLE IF NOT EXISTS `ConditionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `structure_solvent_id` int,
  `temperature` int,
  `environment` varchar(255),
  `pressure` int,
  `reactionTime` time,
  `reaction_id` int,
  FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`),
  FOREIGN KEY (`structure_solvent_id`) REFERENCES `StructureData` (`id`)
);

ALTER TABLE StructureData
ADD INDEX idx_smiles (smiles);

ALTER TABLE ReactionData
ADD INDEX idx_code (code);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
