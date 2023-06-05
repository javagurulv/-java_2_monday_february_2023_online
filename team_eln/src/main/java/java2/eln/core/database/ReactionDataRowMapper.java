package java2.eln.core.database;

import java2.eln.core.domain.ConditionData;
import java2.eln.core.domain.ReactionData;
import java2.eln.core.domain.StructureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// TODO: Refactor to optimize database requests
// Currently, the mapper uses two separate requests to fetch the starting materials and the main product.
// Consider optimizing the code to retrieve all necessary data in a single database request for improved performance.
// Additionally, since it is mentioned starting ORM learning,
// higher-level abstractions for interacting with the database and can simplify the data access code.

@Component
public class ReactionDataRowMapper implements RowMapper<ReactionData> {
    @Autowired private StructureDataRepository structureDataRepository;

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
        int reactionId = rs.getInt("id");
        List<StructureData> startingMaterials = structureDataRepository.getByReactionId(reactionId);
        reactionData.setStartingMaterials(startingMaterials);

        // Set StructureData for the main product
        int reactionProductId = rs.getInt("structure_mainProduct_id");
        if (reactionProductId != 0) {
            StructureData mainProduct = structureDataRepository.getByMainProduct(reactionProductId);
            reactionData.setMainProduct(mainProduct);
        }

        return reactionData;
    }
}
