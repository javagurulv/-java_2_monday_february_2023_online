package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.RemoveDetailRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RemoveDetailServiceTest {

    @Mock private JpaDetailRepository detailRepository;
    @Mock private RemoveDetailRequestValidator validator;
    @InjectMocks private RemoveDetailService service;

    @Test
    public void shouldReturnErrorWhenDetailIdNotProvided() {
        RemoveDetailRequest request = new RemoveDetailRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError(FieldTitle.ID, "Must not be empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveDetailResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals(FieldTitle.ID, response.getErrors().get(0).getField());
        assertEquals("Must not be empty", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldDeleteDetailWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        RemoveDetailRequest request = new RemoveDetailRequest(1L);
        RemoveDetailResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDetailRemoved());
    }
}
