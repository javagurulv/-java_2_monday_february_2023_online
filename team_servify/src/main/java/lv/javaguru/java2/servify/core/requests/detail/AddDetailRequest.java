package lv.javaguru.java2.servify.core.requests.detail;

import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.domain.detail.DetailSideEnum;
import lv.javaguru.java2.servify.domain.detail.DetailTypeEnum;

import java.math.BigDecimal;

public class AddDetailRequest {

    private Long id;

    public AddDetailRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
