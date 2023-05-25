package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.math.BigDecimal;
import java.util.List;

public interface DetailRepository {

    void save(Detail detail);

    boolean deleteById(Long id);

    List<Detail> getAllDetails();

    BigDecimal getTotalPrice(List<Detail> listWithPrices);

}
