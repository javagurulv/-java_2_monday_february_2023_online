package shop.core.services.validators.universal.system;

import org.springframework.stereotype.Component;
import shop.core.services.exception.ServiceMissingDataException;
import shop.core.support.CurrentUserId;

@Component
public class CurrentUserIdValidator {

    public boolean validateCurrentUserIdIsPresent(Long userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

    public boolean validateCurrentUserIdIsPresent(CurrentUserId userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
