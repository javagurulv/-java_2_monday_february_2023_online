package shop.core.services.actions.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.domain.user.User;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignInResponse;
import shop.core.services.validators.actions.shared.SignInValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignInServiceTest {

    @Mock
    private SignInValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private SignInRequest mockRequest;
    @Mock
    private CoreError mockCoreError;
    @Mock
    private User mockUser;
    @Mock
    private CurrentUser mockCurrentUser;

    @InjectMocks
    private SignInService service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        SignInResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        when(mockRequest.getCurrentUser()).thenReturn(mockCurrentUser);
        SignInResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldUpdateCurrentUserId() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockDatabaseAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        when(mockRequest.getCurrentUser()).thenReturn(mockCurrentUser);
        service.execute(mockRequest);
        verify(mockCurrentUser).setUser(any(User.class));
    }

}
