package java2.eln.core.database;


import java2.eln.core.domain.ConditionData;
import java2.eln.core.domain.ReactionData;
import java2.eln.core.domain.StructureData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReactionDataRowMapper implements RowMapper<ReactionData> {

    private final JdbcTemplate jdbcTemplate;

    public ReactionDataRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public ReactionData mapRow(ResultSet rs, int rowNum) throws SQLException {
        var reactionData = new ReactionData(
                rs.getString("code"),
                rs.getString("name")
        );
        // Set ConditionData
        ConditionData conditionData = new ConditionData();
        conditionData.setTemperature(rs.getInt("temperature"));
        conditionData.setEnvironment(rs.getString("environment"));
        conditionData.setPressure(rs.getInt("pressure"));
        reactionData.setConditions(conditionData);

        // Set List<StructureData> startingMaterials
        List<StructureData> startingMaterials = new ArrayList<>();
        int reactionId = rs.getInt("id");
        String sqlGetStartingMaterials = """
                        SELECT *
                        FROM ReactionStartingMaterial
                        LEFT JOIN StructureData ON ReactionStartingMaterial.structure_id = StructureData.id
                        WHERE ReactionStartingMaterial.reaction_id = ?;
                """;
        startingMaterials = jdbcTemplate.query(sqlGetStartingMaterials, new StructureDataRowMapper(), reactionId);

        reactionData.setStartingMaterials(startingMaterials);

        // Set StructureData for the main product
        int reactionProductId = rs.getInt("structure_mainProduct_id");
        if (reactionProductId != 0) {
            String sqlGetProduct = """
                        SELECT * FROM ReactionData AS reactions
                        LEFT JOIN StructureData  as structure
                        ON structure.id = reactions.structure_mainProduct_id
                        WHERE structure_mainProduct_id = ?;
                    """;
            StructureData mainProduct = jdbcTemplate.queryForObject(sqlGetProduct, new StructureDataRowMapper(), reactionProductId);
            reactionData.setMainProduct(mainProduct);
        }

        return reactionData;
    }
}

