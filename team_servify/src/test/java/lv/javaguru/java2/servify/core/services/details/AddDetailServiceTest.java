package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.AddDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.matchers.DetailMatcher;
import lv.javaguru.java2.servify.core.services.validators.AddDetailRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddDetailServiceTest {

    @Mock private JpaDetailRepository detailRepository;
    @Mock private AddDetailRequestValidator validator;
    @InjectMocks private AddDetailService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFailsA() {
        AddDetailRequest notValidRequest = new AddDetailRequest(null, "Side", BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_TYPE, "Not valid Type")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFailsB() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", null, BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_SIDE, "Not valid Side")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFailsC() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", "Side", null);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_PRICE, "Not valid Price")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidatorA() {
        AddDetailRequest notValidRequest = new AddDetailRequest(null, "Side", BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_TYPE, "Not valid Type")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), FieldTitle.DETAIL_TYPE);
        assertEquals(response.getErrors().get(0).getMessage(), "Not valid Type");
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidatorB() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", null, BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_SIDE, "Not valid Side")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), FieldTitle.DETAIL_SIDE);
        assertEquals(response.getErrors().get(0).getMessage(), "Not valid Side");
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidatorC() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", "Side", null);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_PRICE, "Must not be empty!")));
        AddDetailResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), FieldTitle.DETAIL_PRICE);
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFailsA() {
        AddDetailRequest notValidRequest = new AddDetailRequest(null, "Side", BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_TYPE, "Not valid Type")));
        service.execute(notValidRequest);
        verifyNoInteractions(detailRepository);
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFailsB() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", null, BigDecimal.ZERO);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_SIDE, "Not valid Type")));
        service.execute(notValidRequest);
        verifyNoInteractions(detailRepository);
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFailsC() {
        AddDetailRequest notValidRequest = new AddDetailRequest("Type", "Side", null);
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError(FieldTitle.DETAIL_PRICE, "Not valid Type")));
        service.execute(notValidRequest);
        verifyNoInteractions(detailRepository);
    }

    @Test
    public void shouldAddDetailToDatabaseWhenRequestIsValid() {
        AddDetailRequest validRequest = new AddDetailRequest("Type", "Side", BigDecimal.ZERO);
        when(validator.validate(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(detailRepository).save(argThat(new DetailMatcher("Type", "Side", BigDecimal.ZERO)));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddDetailRequest validRequest = new AddDetailRequest("Type", "Side", BigDecimal.ZERO);
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddDetailResponse response = service.execute(validRequest);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnCorrectResponseWhenRequestIsValid() {
        AddDetailRequest validRequest = new AddDetailRequest("Type", "Side", BigDecimal.ZERO);
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddDetailResponse response = service.execute(validRequest);
        assertTrue(response.isAdded());
//        assertNotNull(response.newDetail());
//        assertEquals(response.newDetail().getType(), validRequest.getDetailType());
//        assertEquals(response.newDetail().getSide(), validRequest.getDetailSide());
//        assertEquals(response.newDetail().getPrice(), validRequest.getDetailPrice());
    }

}