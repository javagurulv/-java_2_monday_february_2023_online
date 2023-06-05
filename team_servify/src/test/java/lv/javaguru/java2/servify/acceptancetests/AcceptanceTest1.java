package lv.javaguru.java2.servify.acceptancetests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;

import lv.javaguru.java2.servify.config.ServifyConfiguration;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.services.details.AddDetailService;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServifyConfiguration.class})

public class AcceptanceTest1 {

    @Autowired private AddDetailService addDetailService;
    @Autowired private GetAllDetailsService getAllDetailsService;

    @Test
    @Sql({"/schema.sql"})
    public void shouldReturnCorrectDetailList() {
        AddDetailRequest request1 = new AddDetailRequest("Type1", "Side1", BigDecimal.ZERO);
        addDetailService.execute(request1);

        AddDetailRequest request2 = new AddDetailRequest("Type2", "Side2", BigDecimal.TEN);
        addDetailService.execute(request2);

        GetAllDetailResponse response = getAllDetailsService.getAll();
        assertEquals(2, response.getDetails().size());
    }
}
