package shop.core.services.validators.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.responses.CoreError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartValidatorTest {

    @Mock
    private JpaCartRepository mockJpaCartRepository;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private User mockUser;
    @Mock
    private Cart mockCart;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private CartValidator validator;

    @Test
    void shouldReturnNoError() {
        when(mockJpaCartRepository.findOpenCartByUser(mockUser)).thenReturn(Optional.of(mockCart));
        Optional<CoreError> error = validator.validateOpenCartExistsForUser(mockUser);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnError() {
        when(mockJpaCartRepository.findOpenCartByUser(mockUser)).thenReturn(Optional.empty());
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validateOpenCartExistsForUser(mockUser);
        verify(mockErrorProcessor).getCoreError("button", "VDT-CRT-NOC");
    }

}
