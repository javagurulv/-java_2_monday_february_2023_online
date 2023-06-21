package lv.javaguru.java2.servify.acceptancetests;

import lv.javaguru.java2.servify.core.config.ServifyCoreConfiguration;
import lv.javaguru.java2.servify.core.dto.requests.GetDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetDetailService;
import lv.javaguru.java2.servify.core.services.details.UpdateDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServifyCoreConfiguration.class})
public class AcceptanceTest3 {

    @Autowired private UpdateDetailService updateDetailService;
    @Autowired private GetDetailService getDetailService;

    @Test
    @Sql({"/schema.sql", "/dbDataInsertScript.sql"})
    public void shouldUpdate() {
        UpdateDetailRequest request = new UpdateDetailRequest(1L, "abc", null, BigDecimal.ONE);
        UpdateDetailResponse response = updateDetailService.update(request);

        GetDetailResponse getDetailResponse = getDetailService.execute(new GetDetailRequest(1L));

        assertFalse(response.hasErrors());
        assertEquals(request.getType(), getDetailResponse.getDetailDTO().getType());
        assertEquals(0, BigDecimal.ONE.compareTo
                (new BigDecimal(getDetailResponse.getDetailDTO().getPrice())));
    }

}
