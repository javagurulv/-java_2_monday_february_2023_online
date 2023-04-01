package core.services.actions.customer;

import core.database.Database;
import core.domain.cart.Cart;
import core.domain.cart.CartStatus;
import core.requests.customer.BuyRequest;
import core.responses.CoreError;
import core.responses.customer.BuyResponse;
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.customer.BuyValidator;

import java.time.LocalDate;
import java.util.List;

public class BuyService {

    private final Database database;
    private final BuyValidator validator;

    public BuyService(Database database, BuyValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = getOpenCartForUserId(request.getUserId().getValue());
        database.accessCartDatabase().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        database.accessCartDatabase().changeLastActionDate(cart.getId(), LocalDate.now());
        return new BuyResponse();
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}