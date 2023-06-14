package shop.core.services.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.validators.actions.manager.AddItemToShopValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Transactional
public class AddItemToShopService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private AddItemToShopValidator validator;


    public AddItemToShopResponse execute(AddItemToShopRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToShopResponse(errors);
        }
        String itemName = request.getItemName();
        BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
        Integer availableQuantity = Integer.parseInt(request.getAvailableQuantity());
        itemRepository.save(new Item(itemName, price, availableQuantity));
        return new AddItemToShopResponse();
    }

}
