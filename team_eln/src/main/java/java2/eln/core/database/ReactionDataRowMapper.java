package java2.eln.core.database;


import java2.eln.core.domain.ReactionData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReactionDataRowMapper implements RowMapper<ReactionData> {
    @Override
    public ReactionData mapRow(ResultSet rs, int rowNum) throws SQLException {

        var reactionData = new ReactionData(
                rs.getString("code"),
                rs.getString("name")
        );

        return reactionData;
    }
}

