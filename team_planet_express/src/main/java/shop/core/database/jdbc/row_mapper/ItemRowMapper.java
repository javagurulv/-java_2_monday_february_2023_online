package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.item.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item(
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getInt("available_quantity")
        );
        item.setId(rs.getLong("id"));
        return item;
    }

}
