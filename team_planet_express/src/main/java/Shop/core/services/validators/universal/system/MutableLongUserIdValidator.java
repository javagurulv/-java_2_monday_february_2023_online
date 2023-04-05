package Shop.core.services.validators.universal.system;

import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.support.MutableLong;

public class MutableLongUserIdValidator {

    public boolean validateMutableLongUserIdIsPresent(MutableLong userId) {
        if (userId == null) {
            throw new ServiceMissingDataException();
        }
        return true;
    }

}
