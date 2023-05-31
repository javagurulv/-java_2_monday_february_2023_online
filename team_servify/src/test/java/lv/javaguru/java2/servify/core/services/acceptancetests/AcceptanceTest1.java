package lv.javaguru.java2.servify.core.services.acceptancetests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;

import lv.javaguru.java2.servify.config.ServifyConfiguration;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.services.details.AddDetailService;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServifyConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest1 {

    @Autowired private AddDetailService addDetailService;
    @Autowired private GetAllDetailsService getAllDetailsService;

    @Test
    public void shouldReturnCorrectDetailList() {
        AddDetailRequest addDetailRequest1 = new AddDetailRequest("Type1", "Side1", BigDecimal.ZERO);
        addDetailService.execute(addDetailRequest1);

        AddDetailRequest addDetailRequest2 = new AddDetailRequest("Type2", "Side2", BigDecimal.TEN);
        addDetailService.execute(addDetailRequest2);

        GetAllDetailResponse response = getAllDetailsService.getAll();
        assertEquals(2, response.getDetails().size());
    }
}
