SELECT *
FROM ReactionData
INNER JOIN StructureData ON ReactionData.structure_mainProduct_id = StructureData.id;


SELECT *
FROM structuredata
INNER JOIN reactionstartingmaterial ON reactionstartingmaterial.structure_id = StructureData.id;

SELECT *
FROM ReactionData
INNER JOIN StructureData ON ReactionData.structure_mainProduct_id = StructureData.id;

SELECT * FROM reactiondata
INNER join (
SELECT *
FROM structuredata
INNER JOIN reactionstartingmaterial ON reactionstartingmaterial.structure_id = StructureData.id) as sm
on reactiondata.id = sm.reaction_id