package shop.core.services.validators.services_validators.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core_api.requests.customer.AddItemToCartRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddItemToCartValidator {
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String ERROR_NO_SUCH_ITEM = "VDT-AIC-NIS";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "VDT-AIC-NEQ";
    private static final String ERROR_NO_OPEN_CART = "VDT-CRT-NOC";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(AddItemToCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getCartItemDTO().getItemDTO().getId() != null && !itemRepository.existsById(request.getCartItemDTO().getItemDTO().getId()))
            errors.add(errorProcessor.getCoreError(VALUE_NAME_ITEM, ERROR_NO_SUCH_ITEM));
        if (request.getCartItemDTO().getCartId() != null && !cartRepository.existsById(request.getCartItemDTO().getCartId()))
            errors.add(errorProcessor.getCoreError(VALUE_NAME_ITEM, ERROR_NO_OPEN_CART));
        return errors;
    }

}
