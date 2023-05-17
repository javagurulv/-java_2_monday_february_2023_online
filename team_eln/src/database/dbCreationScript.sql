SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2ELN` DEFAULT CHARACTER SET utf8 ;
USE `java2ELN` ;

CREATE TABLE ReactionData (
  id INT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  conditions_id INT,
  mainProduct_id INT,
  reactionYield DOUBLE,
);

CREATE TABLE ReactionStartingMaterial (
  reaction_id INT,
  structure_id INT,
  FOREIGN KEY (reaction_id) REFERENCES ReactionData(id),
  FOREIGN KEY (structure_id) REFERENCES StructureData(id),
  PRIMARY KEY (reaction_id, structure_id)
);

CREATE TABLE ConditionData (
  id INT AUTO_INCREMENT PRIMARY KEY,
  solvent_id INT,
  temperature INT,
  environment VARCHAR(255),
  pressure INT,
  reactionTime TIME,
  FOREIGN KEY (solvent_id) REFERENCES StructureData (id)
);

CREATE TABLE StructureData (
  id INT AUTO_INCREMENT PRIMARY KEY,
  smiles VARCHAR(255),
  casNumber VARCHAR(255),
  name VARCHAR(255),
  internalCode VARCHAR(255),
  mass DOUBLE
);

ALTER TABLE ReactionData
ADD FOREIGN KEY (conditions_id) REFERENCES ConditionData(id),
ADD FOREIGN KEY (mainProduct_id) REFERENCES StructureData(id);

ENGINE = InnoDB
AUTO_INCREMENT = 1002;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

