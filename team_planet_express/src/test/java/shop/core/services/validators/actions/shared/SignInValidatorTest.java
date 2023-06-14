package shop.core.services.validators.actions.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.User;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.CurrentUserId;
import shop.matchers.InputStringValidatorDataMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInValidatorTest {

    @Mock
    private JpaUserRepository mockJpaUserRepository;
    @Mock
    private CurrentUserIdValidator mockCurrentUserIdValidator;
    @Mock
    private InputStringValidator mockInputStringValidator;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private SignInRequest mockRequest;
    @Mock
    private CurrentUserId mockUserId;
    @Mock
    private User mockUser;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private SignInValidator validator;

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(List.of(mockUser));
        when(mockUser.getPassword()).thenReturn("password");
        when(mockRepositoryAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateLoginNameIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(List.of(mockUser));
        when(mockUser.getPassword()).thenReturn("password");
        when(mockRepositoryAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("login name", "login", "Login name")));
    }

    @Test
    void shouldReturnErrorForNonexistentLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(Collections.emptyList());
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("login", "VDT-SIN-LNE");
    }

    @Test
    void shouldValidatePasswordIsPresent() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(List.of(mockUser));
        when(mockUser.getPassword()).thenReturn("password");
        when(mockRepositoryAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("password", "password", "Password")));
    }

    @Test
    void shouldReturnErrorForWrongPassword() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("wrong password");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(List.of(mockUser));
        when(mockRepositoryAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("password");
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("password", "VDT-SIN-PII");
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorData.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockJpaUserRepository.findByLogin("login name")).thenReturn(List.of(mockUser));
        when(mockRepositoryAccessValidator.getUserByLoginName("login name")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("password");
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
