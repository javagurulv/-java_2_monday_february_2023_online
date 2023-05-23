package shop.core.services.validators.universal.system;

import org.springframework.stereotype.Component;
import shop.core.services.exception.ServiceMissingDataException;
import shop.core.support.CurrentUser;

@Component
public class CurrentUserIdValidator {

    public boolean validateCurrentUserIdIsPresent(CurrentUser userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
