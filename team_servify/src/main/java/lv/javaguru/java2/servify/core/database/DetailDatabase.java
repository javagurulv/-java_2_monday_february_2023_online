package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.detail.Detail;

import java.math.BigDecimal;
import java.util.List;

public interface DetailDatabase {

    void save(Detail detail);

    void deleteById(Long id);

    List<Detail> getAllDetails();

    BigDecimal getTotalPrice(List<Detail> listWithPrices);

}
