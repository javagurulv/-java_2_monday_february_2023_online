package shop.core.services.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.services.validators.actions.manager.ChangeItemDataValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Transactional
public class ChangeItemDataService {

    @Autowired
    private Repository repository;
    @Autowired
    private ChangeItemDataValidator validator;

    public ChangeItemDataResponse execute(ChangeItemDataRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeItemDataResponse(errors);
        }
        Long itemId = Long.parseLong(request.getItemId());
        changeItemName(itemId, request.getNewItemName());
        changePrice(itemId, request.getNewPrice());
        changeAvailableQuantity(itemId, request.getNewAvailableQuantity());
        return new ChangeItemDataResponse();
    }

    private void changeItemName(Long itemId, String newItemName) {
        if (newItemName != null && !newItemName.isBlank()) {
            repository.accessItemRepository().changeName(itemId, newItemName);
        }
    }

    private void changePrice(Long itemId, String newPriceString) {
        if (newPriceString != null && !newPriceString.isBlank()) {
            BigDecimal newPrice = new BigDecimal(newPriceString).setScale(2, RoundingMode.HALF_UP);
            repository.accessItemRepository().changePrice(itemId, newPrice);
        }
    }

    private void changeAvailableQuantity(Long itemId, String newAvailableQuantityString) {
        if (newAvailableQuantityString != null && !newAvailableQuantityString.isBlank()) {
            Integer newAvailableQuantity = Integer.parseInt(newAvailableQuantityString);
            repository.accessItemRepository().changeAvailableQuantity(itemId, newAvailableQuantity);
        }
    }

}
