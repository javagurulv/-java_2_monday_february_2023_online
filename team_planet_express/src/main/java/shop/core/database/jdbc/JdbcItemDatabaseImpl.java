package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import shop.core.database.ItemDatabase;
import shop.core.database.jdbc.row_mapper.ItemRowMapper;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcItemDatabaseImpl implements ItemDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO item (name, price, available_quantity) VALUES (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setBigDecimal(2, item.getPrice());
            statement.setInt(3, item.getAvailableQuantity());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().longValue());
        }
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        String sql = "SELECT * FROM item WHERE id = ?;";
        Object[] args = new Object[]{itemId};
        return jdbcTemplate.query(sql, new ItemRowMapper(), args).stream().findFirst();
    }

    @Override
    public Optional<Item> findByName(String name) {
        String sql = "SELECT * FROM item WHERE name = ?;";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, new ItemRowMapper(), args).stream().findFirst();
    }

    //TODO 死んだ
    @Override
    public void changeName(Long id, String newName) {
        String sql = "UPDATE item SET name = ? WHERE id = ?;";
        Object[] args = new Object[]{newName, id};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        String sql = "UPDATE item SET price = ? WHERE id = ?;";
        Object[] args = new Object[]{newPrice, id};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        String sql = "UPDATE item SET available_quantity = ? WHERE id = ?;";
        Object[] args = new Object[]{newAvailableQuantity, id};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public List<Item> getAllItems() {
        String sql = "SELECT * FROM item;";
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    @Override
    public List<Item> searchByName(String itemName) {
        String sql = "SELECT * FROM item WHERE LOWER(name) LIKE ?;";
        Object[] args = new Object[]{"%" + itemName + "%"};
        return jdbcTemplate.query(sql, new ItemRowMapper(), args);
    }

    //TODO Blank works ?
    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
        String sql = "SELECT * FROM item WHERE LOWER(name) LIKE ? AND price <= ?;";
        Object[] args = new Object[]{"%" + itemName + "%", price};
        return jdbcTemplate.query(sql, new ItemRowMapper(), args);
    }

}
