package java2.eln.core.database;

import java2.eln.core.domain.ReactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class JdbcDatabaseImpl implements DatabaseIM {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addReaction(ReactionData reaction) {
        String querySQL = "INSERT INTO ReactionData (code, name, structure_mainProduct_id, reactionYield) " +
                "VALUES (?, ?, ?, ?)";
        int userId = (reaction.getUser() != null) ? reaction.getUser().getUserId() : 0;
        jdbcTemplate.update(querySQL, reaction.getCode(), reaction.getName(), reaction.getMainProduct().getId(),
                reaction.getReactionYield());
    }


    @Override
    public boolean delReactionByCode(String code) {
        return false;
    }

    @Override
    public List<ReactionData> getAllReactions() {
        return null;
    }

    @Override
    public boolean hasReactionWithCode(String reactionCode) {
        return false;
    }
}