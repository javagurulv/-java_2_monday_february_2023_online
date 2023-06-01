package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailsRowMapper implements RowMapper<Detail> {
    @Override
    public Detail mapRow(ResultSet rs, int rowNum) throws SQLException {
        Detail detail = new Detail();
        detail.setId(rs.getLong("id"));
        detail.setType(rs.getString("detail_type"));
        detail.setSide(rs.getString("detail_side"));
        detail.setPrice(rs.getBigDecimal("price"));
        return detail;
    }
}
