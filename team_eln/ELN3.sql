CREATE TABLE `ReactionData` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `structure_mainProduct_id` int,
  `reactionYield` double
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
  `reaction_id` int
);

ALTER TABLE `ConditionData` ADD FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionData` (`structure_mainProduct_id`);

ALTER TABLE `ReactionData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`reaction_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ReactionStartingMaterial` (`structure_id`);

ALTER TABLE `StructureData` ADD FOREIGN KEY (`id`) REFERENCES `ConditionData` (`structure_solvent_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`);
