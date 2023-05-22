package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.domain.detail.DetailSideEnum;
import lv.javaguru.java2.servify.domain.detail.DetailTypeEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailsRowMapper implements RowMapper<Detail> {
    @Override
    public Detail mapRow(ResultSet rs, int rowNum) throws SQLException {
        Detail detail = new Detail();
        detail.setId(rs.getLong("id"));
        detail.setType(DetailTypeEnum.valueOf(rs.getString("detail_type_id")));
        detail.setSide(DetailSideEnum.valueOf(rs.getString("detail_side_id")));
        detail.setPrice(rs.getBigDecimal("price"));
        return detail;
    }
}
