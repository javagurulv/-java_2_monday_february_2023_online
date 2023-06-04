package lv.javaguru.java2.servify.core.database.jpa;

import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JpaDetailRepository extends JpaRepository<Detail, Long> {
//    List<Detail> findByDetailType(String detailType);
//    List<Detail> findByDetailSide(String detailSide);
//    List<Detail> findByDetailTypeSide(String detailType, String detailSide);
    //List<Detail> findByDetailPrice(BigDecimal detailPrice);
    //List<Detail> findByDetailTypePrice(String detailType, BigDecimal detailPrice);
}
