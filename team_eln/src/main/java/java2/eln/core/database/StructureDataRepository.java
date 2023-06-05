package java2.eln.core.database;

import java2.eln.core.domain.StructureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StructureDataRepository {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private StructureDataRowMapper structureDataRowMapper;

    public List<StructureData> getByReactionId(int reactionId) {
        String sqlGetStartingMaterials = """
                        SELECT *
                        FROM ReactionStartingMaterial
                        LEFT JOIN StructureData ON ReactionStartingMaterial.structure_id = StructureData.id
                        WHERE ReactionStartingMaterial.reaction_id = ?;
                """;
        return jdbcTemplate.query(sqlGetStartingMaterials, structureDataRowMapper, reactionId);
    }

    public StructureData getByMainProduct(int reactionProductId) {
        String sqlGetProduct = """
                        SELECT * FROM ReactionData AS reactions
                        LEFT JOIN StructureData  as structure
                        ON structure.id = reactions.structure_mainProduct_id
                        WHERE structure_mainProduct_id = ?;
                    """;
        return jdbcTemplate.queryForObject(sqlGetProduct, structureDataRowMapper, reactionProductId);
    }

}
