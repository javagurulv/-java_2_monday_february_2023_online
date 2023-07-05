package shop.core.services.validators.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core_api.responses.CoreError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartValidatorTest {

    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private Cart mockCart;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private CartValidator validator;

    @Test
    void shouldReturnNoError() {
        when(mockCartRepository.findOne(any(Specification.class))).thenReturn(Optional.of(mockCart));
        Optional<CoreError> error = validator.validateOpenCartExistsForUserId(1L);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnError() {
        when(mockCartRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validateOpenCartExistsForUserId(1L);
        verify(mockErrorProcessor).getCoreError("button", "VDT-CRT-NOC");
    }

}
