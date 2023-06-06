package shop.core.services.validators.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.services.actions.shared.SecurityService;
import shop.core.support.CurrentUserId;

@ExtendWith(MockitoExtension.class)
class ListShopItemsValidatorTest {

    @Mock
    private SecurityService mockSecurityService;
    @Mock
    private ListShopItemsRequest mockRequest;
    @Mock
    private CurrentUserId mockUserId;

    @InjectMocks
    private ListShopItemsValidator validator;

    @Test
    @WithMockUser
    void shouldValidateUserIdIsPresent() {
        //when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        validator.validate(mockRequest);
        //verify(mockCurrentUserIdValidator).validateCurrentUserIdIsPresent(mockUserId);
    }

}
