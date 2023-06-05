package java2.eln.core.database;

import java2.eln.core.domain.ConditionData;

import java2.eln.core.domain.StructureData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

// TODO: This class is not yet ready. Work in progress. need to add solvent as StructureData
public class ConditionDataDataRowMapper implements RowMapper<ConditionData> {
    @Override
    public ConditionData mapRow(ResultSet rs, int rowNum) throws SQLException {
        StructureData solvent = null;

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
