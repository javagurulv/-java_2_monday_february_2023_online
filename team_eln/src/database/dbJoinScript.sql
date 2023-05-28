SELECT *
FROM ReactionData
INNER JOIN StructureData ON ReactionData.structure_mainProduct_id = StructureData.id;