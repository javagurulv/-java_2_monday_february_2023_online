package shop.core.services.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.validators.actions.manager.AddItemToShopValidator;
import shop.core_api.entry_point.manager.AddItemToShopService;
import shop.core_api.requests.manager.AddItemToShopRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.AddItemToShopResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class AddItemToShopServiceImpl implements AddItemToShopService{

    @Autowired
    private ItemRepository itemRepository;
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
