package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.detail.Detail;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryDetailDatabaseImpl implements DetailDatabase {

    private Long nextId = 1L;
    private final List<Detail> details = new ArrayList<>();

    @Override
    public void save(Detail detail) {
        detail.setId(nextId);
        nextId++;
        details.add(detail);
    }

    @Override
    public void deleteById(Long id) {
        details.stream()
                .filter(detail -> detail.getId().equals(id))
                .findFirst()
                .ifPresent(details::remove);
    }

    @Override
    public List<Detail> getAllDetails() {
        return details;
    }

    @Override
    public BigDecimal getTotalPrice(List<Detail> listWithPrices) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Detail detail : listWithPrices) {
            if (details.contains(detail)) {
                totalPrice = totalPrice.add(detail.getPrice());
            }
        }
        return totalPrice;
    }
}
