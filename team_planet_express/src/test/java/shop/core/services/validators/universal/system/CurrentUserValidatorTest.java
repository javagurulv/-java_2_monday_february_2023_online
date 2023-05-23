package shop.core.services.validators.universal.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.services.exception.ServiceMissingDataException;
import shop.core.support.CurrentUser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CurrentUserValidatorTest {

    @Mock
    private CurrentUser mockUserId;

    @InjectMocks
    private CurrentUserIdValidator validator;

    @Test
    void shouldThrowMissingDataException() {
        assertThrows(ServiceMissingDataException.class, () -> validator.validateCurrentUserIdIsPresent(null));
    }

    @Test
    void shouldReturnTrueIfUserIdIsPresent() {
        assertTrue(validator.validateCurrentUserIdIsPresent(mockUserId));
    }

}
