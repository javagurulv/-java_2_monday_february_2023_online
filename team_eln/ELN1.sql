CREATE TABLE `ReactionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `conditions_id` int,
  `mainProduct_id` int,
  `reactionYield` double
);

CREATE TABLE `ReactionStartingMaterial` (
  `reaction_id` int,
  `structure_id` int,
  PRIMARY KEY (`reaction_id`, `structure_id`)
);

CREATE TABLE `ConditionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `solvent_id` int,
  `temperature` int,
  `environment` varchar(255),
  `pressure` int,
  `reactionTime` time
);

CREATE TABLE `StructureData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `smiles` varchar(255),
  `casNumber` varchar(255),
  `name` varchar(255),
  `internalCode` varchar(255),
  `mass` double
);

ALTER TABLE `ConditionData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionData` (`conditions_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionData` (`mainProduct_id`);

ALTER TABLE `ReactionData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`reaction_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`structure_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ConditionData` (`solvent_id`);
