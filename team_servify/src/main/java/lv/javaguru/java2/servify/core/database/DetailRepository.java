package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.math.BigDecimal;
import java.util.List;

public interface DetailRepository {

    void save(Detail detail);

    boolean deleteById(Long id);

    List<Detail> getAllDetails();

    List<Detail> findByDetailType(String detailType);
    List<Detail> findByDetailSide(String detailSide);
    List<Detail> findByDetailTypeSide(String detailType, String detailSide);
    List<Detail> findByDetailPrice(BigDecimal detailPrice);
    List<Detail> findByDetailTypePrice(String detailType, BigDecimal detailPrice);
}
