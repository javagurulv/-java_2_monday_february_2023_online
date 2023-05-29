package lv.javaguru.java2.servify.core.services.matchers;

import lv.javaguru.java2.servify.core.domain.Detail;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;

public class DetailMatcher implements ArgumentMatcher<Detail> {

    private String type;
    private String side;
    private BigDecimal price;

    public DetailMatcher(String type, String side, BigDecimal price) {
        this.type = type;
        this.side = side;
        this.price = price;
    }

    @Override
    public boolean matches(Detail detail) {
        return detail.getType().equals(type)
                && detail.getSide().equals(side)
                && detail.getPrice().equals(price);
    }

}
