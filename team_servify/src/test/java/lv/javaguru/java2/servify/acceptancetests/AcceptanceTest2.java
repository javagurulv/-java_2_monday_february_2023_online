package lv.javaguru.java2.servify.acceptancetests;

import lv.javaguru.java2.servify.config.ServifyConfiguration;
import lv.javaguru.java2.servify.core.dto.requests.SearchDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.SearchDetailResponse;
import lv.javaguru.java2.servify.core.services.details.SearchDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServifyConfiguration.class})
public class AcceptanceTest2 {

    @Autowired private SearchDetailService searchDetailService;

    @Test
    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
    public void searchDetailsByType() {
        SearchDetailRequest request = new SearchDetailRequest("DOOR", null, null);
        SearchDetailResponse response = searchDetailService.execute(request);

        assertEquals(4, response.getDetails().size());
        assertEquals("FRONT_LEFT", response.getDetails().get(0).getSide());
        assertEquals("FRONT_RIGHT", response.getDetails().get(1).getSide());
        assertEquals("REAR_LEFT", response.getDetails().get(2).getSide());
        assertEquals("REAR_RIGHT", response.getDetails().get(3).getSide());
    }

    @Test
    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
    public void searchDetailsBySide() {
        SearchDetailRequest request = new SearchDetailRequest(null, "FRONT_LEFT", null);
        SearchDetailResponse response = searchDetailService.execute(request);

        assertEquals(2, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("WING", response.getDetails().get(1).getType());
    }

    @Test
    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
    public void searchDetailsByPrice() {
        SearchDetailRequest request = new SearchDetailRequest(null, null, BigDecimal.valueOf(130));
        SearchDetailResponse response = searchDetailService.execute(request);

        assertEquals(2, response.getDetails().size());
        assertEquals("WING", response.getDetails().get(0).getType());
        assertEquals("WING", response.getDetails().get(1).getType());
    }

}
