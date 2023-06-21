package lv.javaguru.java2.servify.acceptancetests;

import lv.javaguru.java2.servify.core.config.ServifyCoreConfiguration;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.details.RemoveDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServifyCoreConfiguration.class})
public class AcceptanceTest2 {

    @Autowired private RemoveDetailService removeDetailService;
    @Autowired private GetAllDetailsService getAllDetailsService;

    @Test
    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
    public void shouldDelete() {
        RemoveDetailRequest removeRequest1 = new RemoveDetailRequest(1L);
        RemoveDetailRequest removeRequest2 = new RemoveDetailRequest(4L);

        RemoveDetailResponse removeResponse1 = removeDetailService.execute(removeRequest1);
        RemoveDetailResponse removeResponse2 = removeDetailService.execute(removeRequest2);

        GetAllDetailResponse allDetailResponse = getAllDetailsService.getAll(new GetAllDetailsRequest());

        assertTrue(removeResponse1.isDetailRemoved());
        assertTrue(removeResponse2.isDetailRemoved());
        assertEquals(13, allDetailResponse.getDetails().size());
    }

//    @Autowired private SearchDetailService searchDetailService;
//
//    @Test
//    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
//    public void searchDetailsByType() {
//        SearchDetailRequest request = new SearchDetailRequest("DOOR", null, null);
//        SearchDetailResponse response = searchDetailService.execute(request);
//
//        assertEquals(4, response.getDetails().size());
//        assertEquals("FRONT_LEFT", response.getDetails().get(0).getSide());
//        assertEquals("FRONT_RIGHT", response.getDetails().get(1).getSide());
//        assertEquals("REAR_LEFT", response.getDetails().get(2).getSide());
//        assertEquals("REAR_RIGHT", response.getDetails().get(3).getSide());
//    }
//
//    @Test
//    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
//    public void searchDetailsBySide() {
//        SearchDetailRequest request = new SearchDetailRequest(null, "FRONT_LEFT", null);
//        SearchDetailResponse response = searchDetailService.execute(request);
//
//        assertEquals(2, response.getDetails().size());
//        assertEquals("DOOR", response.getDetails().get(0).getType());
//        assertEquals("WING", response.getDetails().get(1).getType());
//    }
//
//    @Test
//    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
//    public void searchDetailsByPrice() {
//        SearchDetailRequest request = new SearchDetailRequest(null, null, BigDecimal.valueOf(130));
//        SearchDetailResponse response = searchDetailService.execute(request);
//
//        assertEquals(2, response.getDetails().size());
//        assertEquals("WING", response.getDetails().get(0).getType());
//        assertEquals("WING", response.getDetails().get(1).getType());
//    }

}
