package shop.core.services.validators.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.CartRepository;
import shop.core.domain.user.User;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core_api.responses.CoreError;

import java.util.Optional;

import static shop.core.database.specifications.CartSpecs.findOpenCartForUser;

@Component
public class CartValidator {

    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_NO_OPEN_CART = "VDT-CRT-NOC";

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ErrorProcessor errorProcessor;

    public Optional<CoreError> validateOpenCartExistsForUserId(Long userId) {
        User user = new User();
        user.setId(userId);
        return (cartRepository.findOne(findOpenCartForUser(user)).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_BUTTON, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }

}
