package java2.eln.core.database;

import java2.eln.core.domain.ReactionData;
import java2.eln.core.domain.StructureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
class JdbcDatabaseImpl implements DatabaseIM {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addReaction(ReactionData reaction) {
        String reactionQuery = "INSERT INTO ReactionData (code, name, reactionYield) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(reactionQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, reaction.getCode());
            ps.setString(2, reaction.getName());
            ps.setDouble(3, reaction.getReactionYield());
            return ps;
        }, keyHolder);

        int reactionId = Objects.requireNonNull(keyHolder.getKey()).intValue();

        int mainProductId = saveStructureData(reaction.getMainProduct());
        updateReactionMainProduct(reactionId, mainProductId);

        reaction.getStartingMaterials().stream()
                .map(this::saveStructureData)
                .forEach(structureId -> saveReactionStartingMaterial(reactionId, structureId));
    }

    @Override
    public boolean delReactionByCode(String code) {
        return false;
    }

    @Override
    public boolean delReactionByID(int id) {
        String reactionQuery1 = "DELETE FROM ReactionStartingMaterial WHERE reaction_id = ?";
        String reactionQuery2 = "DELETE FROM ConditionData WHERE reaction_id = ?";
        String reactionQuery3 = "DELETE FROM ReactionData WHERE id = ?";

        int affectedRows1 = jdbcTemplate.update(reactionQuery1, id);
        int affectedRows2 = jdbcTemplate.update(reactionQuery2, id);
        int affectedRows3 = jdbcTemplate.update(reactionQuery3, id);

        System.out.println(affectedRows1 );
        System.out.println(affectedRows2);
        System.out.println(affectedRows3);

        return affectedRows1 > 0 && affectedRows2 > 0 && affectedRows3 > 0;
    }

    @Override
    public List<ReactionData> getAllReactions() {
        return null;
    }

    @Override
    public boolean hasReactionWithCode(String reactionCode) {
        String query = "SELECT COUNT(*) FROM ReactionData WHERE code = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, reactionCode);
        return count != null && count > 0;
    }

    @Override
    public boolean hasReactionWithId(int reactionId) {
        String query = "SELECT COUNT(*) FROM ReactionData WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, reactionId);
        return count != null && count > 0;
    }

    private int saveStructureData(StructureData structureData) {
        String structureQuery = "INSERT INTO `StructureData` (smiles, casNumber, name, internalCode, mass) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(structureQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, structureData.getSmiles());
            ps.setString(2, structureData.getCasNumber());
            ps.setString(3, structureData.getName());
            ps.setString(4, structureData.getInternalCode());
            ps.setDouble(5, structureData.getMass());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private void updateReactionMainProduct(int reactionId, int mainProductId) {
        String updateQuery = "UPDATE `ReactionData` SET `structure_mainProduct_id` = ? WHERE `id` = ?";
        jdbcTemplate.update(updateQuery, mainProductId, reactionId);
    }

    private void saveReactionStartingMaterial(int reactionId, int structureId) {
        String reactionStartingMaterialQuery = "INSERT INTO `ReactionStartingMaterial` (reaction_id, structure_id) VALUES (?, ?)";
        jdbcTemplate.update(reactionStartingMaterialQuery, reactionId, structureId);
    }
}