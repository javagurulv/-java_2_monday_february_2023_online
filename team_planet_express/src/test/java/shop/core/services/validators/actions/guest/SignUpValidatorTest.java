package shop.core.services.validators.actions.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Repository;
import shop.core.database.UserRepository;
import shop.core.domain.user.User;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.CurrentUserId;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.matchers.InputStringValidatorDataMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpValidatorTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private CurrentUserIdValidator mockCurrentUserIdValidator;
    @Mock
    private InputStringValidator mockInputStringValidator;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private SignUpRequest mockRequest;
    @Mock
    private CurrentUserId mockUserId;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private User mockUser;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private SignUpValidator validator;

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        validator.validate(mockRequest);
        verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

    @Test
    void shouldValidateName() {
        when(mockRequest.getName()).thenReturn("name");
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("name", "name", "Name")));
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("name", "name", "Name")), anyInt());
    }

    @Test
    void shouldValidateLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login name");
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("login name", "login", "Login name")));
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("login name", "login", "Login name")), anyInt());
    }

    @Test
    void shouldReturnErrorForExistingLoginName() {
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.findByLoginName("login")).thenReturn(Optional.of(mockUser));
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("login", "VDT-SUP-LAE");
    }

    @Test
    void shouldValidatePassword() {
        when(mockRequest.getPassword()).thenReturn("password");
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("password", "password", "Password")));
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("password", "password", "Password")), anyInt());
    }

    @Test
    void shouldReturnErrorForShortPassword() {
        when(mockRequest.getPassword()).thenReturn("pa");
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("password", "VDT-SUP-PTS");
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorData.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
