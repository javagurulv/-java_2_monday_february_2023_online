package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GetTotalPriceService {

    @Autowired private DetailRepository detailRepository;

    public BigDecimal execute() {
        List<Detail> listWithPrices = detailRepository.getAllDetails();
        return listWithPrices.stream()
                .map(Detail::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
