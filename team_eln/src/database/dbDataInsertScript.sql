--SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checks
--SET FOREIGN_KEY_CHECKS = 1; -- Re-enable foreign key checks

INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass)
VALUES ('CC(=O)OC(=O)C', '108-24-7', 'Acetic anhydride', '124', 3.06);

INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass)
VALUES ('CC(C)CC1=CC=CC=C1', '538-93-2', '(2-Methylpropyl)benzene', '123', 4.03);

INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass)
VALUES ('[Al](Cl)(Cl)Cl1', '7446-70-0', 'Aluminium chloride (anhydrous)', '125', 5.40);

INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass)
VALUES ('CC(C)CC1=CC=C(C=C1)C(=O)C', '7446-70-0', '4-Isobutylacetophenone', '126', 1.10);

INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass)
VALUES ('ClCCl', '75-09-2', 'dichloromethane', '126', 26.60);

INSERT INTO `reactiondata` (code, name, structure_mainProduct_id)
VALUES ('TP1', 'The Friedel-Crafts acylation', (SELECT id FROM structuredata WHERE smiles = 'CC(C)CC1=CC=C(C=C1)C(=O)C'));

INSERT INTO `ConditionData` (structure_solvent_id, temperature, environment, pressure, reactionTime, reaction_id)
VALUES (
    (SELECT id FROM `StructureData` WHERE smiles = 'ClCCl'),
    0,
    'air',
    1,
    TIME('00:45:00'),
    (SELECT id FROM `ReactionData` WHERE code = 'TP1')
);

INSERT INTO `ReactionStartingMaterial` (reaction_id, structure_id)
VALUES (
    (SELECT id FROM `ReactionData` WHERE code = 'TP1'),
    (SELECT id FROM `StructureData` WHERE smiles = 'CC(C)CC1=CC=CC=C1')
);

INSERT INTO `ReactionStartingMaterial` (reaction_id, structure_id)
VALUES (
    (SELECT id FROM `ReactionData` WHERE code = 'TP1'),
    (SELECT id FROM `StructureData` WHERE smiles = 'CC(=O)OC(=O)')
);

INSERT INTO `ReactionStartingMaterial` (reaction_id, structure_id)
VALUES (
    (SELECT id FROM `ReactionData` WHERE code = 'TP1'),
    (SELECT id FROM `StructureData` WHERE smiles = '[Al](Cl)(Cl)Cl1')
);


CREATE TABLE IF NOT EXISTS `ReactionStartingMaterial` (
  `reaction_id` int,
  `structure_id` int,
  PRIMARY KEY (`reaction_id`, `structure_id`),
  FOREIGN KEY (`reaction_id`) REFERENCES `ReactionData` (`id`),
  FOREIGN KEY (`structure_id`) REFERENCES `StructureData` (`id`)
);


--SELECT reactiondata.code, reactiondata.name AS reaction_name, structuredata.name AS product
--FROM reactiondata
--JOIN structuredata ON reactiondata.structure_mainProduct_id = structuredata.id;
