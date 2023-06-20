package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.AddDetailRequestValidator;
import lv.javaguru.java2.servify.core.services.validators.UpdateDetailRequestValidator;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Ignore
@ExtendWith(MockitoExtension.class)
public class UpdateDetailServiceTest {

//    @Mock private JpaDetailRepository repository;
//    @InjectMocks private UpdateDetailService updateDetailService;
//    @InjectMocks private AddDetailService addDetailService;
//    @Mock private UpdateDetailRequestValidator updateDetailRequestValidator;
//    @Mock private AddDetailRequestValidator addDetailRequestValidator;
//
//    @Test
//    public void shouldReturnErrorWithIncorrectPrice() {
//        List<CoreError> errors = new ArrayList<>();
//        errors.add(new CoreError(FieldTitle.DETAIL_PRICE, "Must not be 0 or less"));
//
//        Detail detail = new Detail("Type", "Side", BigDecimal.ONE);
//        detail.setId(1L);
//        repository.saveAndFlush(detail);
//
//        UpdateDetailRequest requestInvalidPrice = new UpdateDetailRequest(1L, null, null, BigDecimal.ZERO);
//        UpdateDetailResponse response = updateDetailService.update(requestInvalidPrice);
//
//        Mockito.when(updateDetailRequestValidator.validate(requestInvalidPrice)).thenReturn(errors);
//
//        assertTrue(response.hasErrors());
//        assertEquals(1, response.getErrors().size());
//        assertEquals(FieldTitle.DETAIL_PRICE, response.getErrors().get(0).getField());
//        assertEquals("Must not be 0 or less", response.getErrors().get(0).getMessage());
//    }

}
