package shop.core.services.validators.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.responses.CoreError;
import shop.core.support.ErrorCodeUtil;

import java.util.Optional;

@Component
public class CartValidator {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private Database database;

    public Optional<CoreError> validateOpenCartExistsForUserId(Long userId) {
        return (database.accessCartDatabase().findOpenCartForUserId(userId).isEmpty())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_16"))
                : Optional.empty();
    }

}
