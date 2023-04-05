package Shop.core.services.actions.manager;

import Shop.core.database.Database;
import Shop.core.domain.item.Item;
import Shop.core.requests.manager.AddItemToShopRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.manager.AddItemToShopResponse;
import Shop.core.services.validators.actions.manager.AddItemToShopValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AddItemToShopService {

    private final Database database;
    private final AddItemToShopValidator validator;

    public AddItemToShopService(Database database, AddItemToShopValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddItemToShopResponse execute(AddItemToShopRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToShopResponse(errors);
        }
        String itemName = request.getItemName();
        BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
        Integer availableQuantity = Integer.parseInt(request.getAvailableQuantity());
        database.accessItemDatabase().save(new Item(itemName, price, availableQuantity));
        return new AddItemToShopResponse();
    }

}