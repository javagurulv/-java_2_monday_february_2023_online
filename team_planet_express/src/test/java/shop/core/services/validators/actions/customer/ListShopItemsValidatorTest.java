package shop.core.services.validators.actions.customer;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.requests.customer.ListShopItemsRequest;

@ExtendWith(MockitoExtension.class)
class ListShopItemsValidatorTest {

    @Mock
    private ListShopItemsRequest mockRequest;

    @InjectMocks
    private ListShopItemsValidator validator;

    //

}
