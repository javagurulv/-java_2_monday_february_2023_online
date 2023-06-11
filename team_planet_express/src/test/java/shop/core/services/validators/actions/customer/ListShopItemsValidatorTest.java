package shop.core.services.validators.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.support.CurrentUserId;
import shop.core_api.requests.customer.GetListShopItemsRequest;

@ExtendWith(MockitoExtension.class)
class ListShopItemsValidatorTest {

    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private GetListShopItemsRequest mockRequest;
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
