package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class JdbcRepositoryImpl implements DetailRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Detail detail) {
        jdbcTemplate.update(
                "INSERT INTO details (detail_type, detail_side, price"
                + "VALUES (?, ?, ?)",
                detail.getType(), detail.getSide(), detail.getPrice()
        );
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM details WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public List<Detail> getAllDetails() {
        return jdbcTemplate.query(
                "SELECT * FROM details",
                new DetailsRowMapper()
        );
    }

    @Override
    public BigDecimal getTotalPrice(List<Detail> listWithPrices) {
        return null;
    }
}
