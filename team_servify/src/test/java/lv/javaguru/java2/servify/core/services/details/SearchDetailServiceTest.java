package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.SearchDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.SearchDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.SearchDetailRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchDetailServiceTest {

    @Mock
    private DetailRepository detailRepository;
    @Mock
    private SearchDetailRequestValidator validator;
    @InjectMocks
    private SearchDetailService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchDetailRequest request = new SearchDetailRequest(null, null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError(FieldTitle.DETAIL_TYPE, "Must not be empty!"));
        errors.add(new CoreError(FieldTitle.DETAIL_SIDE, "Must not be empty!"));
        errors.add(new CoreError(FieldTitle.DETAIL_PRICE, "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchDetailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(3, response.getErrors().size());

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(detailRepository);
    }

    @Test
    public void shouldSearchByType() {
        SearchDetailRequest request = new SearchDetailRequest("DOOR", null, null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailType("DOOR")).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }

    @Test
    public void shouldSearchBySide() {
        SearchDetailRequest request = new SearchDetailRequest(null, "FRONT_RIGHT", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailSide("FRONT_RIGHT")).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }

    @Test
    public void shouldSearchByPrice() {
        SearchDetailRequest request = new SearchDetailRequest(null, null, BigDecimal.ONE);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailPrice(BigDecimal.ONE)).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }

    @Test
    public void shouldSearchByTypeAndSide() {
        SearchDetailRequest request = new SearchDetailRequest("DOOR", "FRONT_RIGHT", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailTypeSide("DOOR", "FRONT_RIGHT")).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }

    @Test
    public void shouldSearchByTypeAndPrice() {
        SearchDetailRequest request = new SearchDetailRequest("DOOR", null, BigDecimal.ONE);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailTypePrice("DOOR", BigDecimal.ONE)).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }

    @Test
    public void shouldSearchBySideAndPrice() {
        SearchDetailRequest request = new SearchDetailRequest(null, "FRONT_RIGHT", BigDecimal.ONE);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Detail> details = new ArrayList<>();
        details.add(new Detail("DOOR", "FRONT_RIGHT", BigDecimal.ONE));
        Mockito.when(detailRepository.findByDetailSidePrice("FRONT_RIGHT", BigDecimal.ONE)).thenReturn(details);

        SearchDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getDetails().size());
        assertEquals("DOOR", response.getDetails().get(0).getType());
        assertEquals("FRONT_RIGHT", response.getDetails().get(0).getSide());
    }
}
