package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//@Repository
public class JdbcRepositoryImpl implements DetailRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Detail detail) {
        jdbcTemplate.update(
                "INSERT INTO details (detail_type, detail_side, price)"
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
    public Optional<Detail> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Detail> getAllDetails() {
        return jdbcTemplate.query(
                "SELECT * FROM details",
                new DetailsRowMapper()
        );
    }

    @Override
    public List<Detail> findByDetailType(String detailType) {
        return null;
    }

    @Override
    public List<Detail> findByDetailSide(String detailSide) {
        return null;
    }

    @Override
    public List<Detail> findByDetailTypeSide(String detailType, String detailSide) {
        return null;
    }

    @Override
    public List<Detail> findByDetailPrice(BigDecimal detailPrice) {
        return null;
    }

    @Override
    public List<Detail> findByDetailTypePrice(String detailType, BigDecimal detailPrice) {
        return null;
    }

}
