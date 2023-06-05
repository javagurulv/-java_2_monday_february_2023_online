package java2.eln.core.database;

import java2.eln.core.domain.StructureData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StructureDataRowMapper implements RowMapper<StructureData> {
    @Override
    public StructureData mapRow(ResultSet rs, int rowNum) throws SQLException {
        String smiles = rs.getString("smiles");
        String casNumber = rs.getString("casNumber");
        String name = rs.getString("name");
        String internalCode = rs.getString("internalCode");
        double mass = rs.getDouble("mass");

        StructureData structureData = new StructureData(smiles);
        structureData.setCasNumber(casNumber);
        structureData.setName(name);
        structureData.setInternalCode(internalCode);
        structureData.setMass(mass);

        return structureData;
    }
}
