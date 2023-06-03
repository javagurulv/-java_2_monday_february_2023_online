package java2.eln.core.database;

import java2.eln.core.domain.ConditionData;

import java2.eln.core.domain.StructureData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

public class ConditionDataDataRowMapper implements RowMapper<ConditionData> {
    @Override
    public ConditionData mapRow(ResultSet rs, int rowNum) throws SQLException {
        StructureData solvent = null;
//        if (rs.getInt("structure_solvent_id") != 0) {
//            solvent = new StructureData("");
//            solvent.setId(rs.getInt("structure_solvent_id"));
//        }

        int temperature = rs.getInt("temperature");
        String environment = rs.getString("environment");
        int pressure = rs.getInt("pressure");
        Duration reactionTime = Duration.parse(rs.getString("reactionTime"));

        ConditionData conditionData = new ConditionData();
        conditionData.setSolvent(solvent);
        conditionData.setTemperature(temperature);
        conditionData.setEnvironment(environment);
        conditionData.setPressure(pressure);
        conditionData.setReactionTime(reactionTime);

        return conditionData;
    }
}
