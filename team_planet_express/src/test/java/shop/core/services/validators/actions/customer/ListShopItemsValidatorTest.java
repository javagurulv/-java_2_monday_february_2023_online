package shop.core.services.validators.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.support.CurrentUserId;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListShopItemsValidatorTest {

    @Mock
    private CurrentUserIdValidator mockCurrentUserIdValidator;
    @Mock
    private ListShopItemsRequest mockRequest;
    @Mock
    private CurrentUserId mockUserId;

    @InjectMocks
    private ListShopItemsValidator validator;

    @Test
    void shouldValidateUserIdIsPresent() {
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        validator.validate(mockRequest);
        verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

}
