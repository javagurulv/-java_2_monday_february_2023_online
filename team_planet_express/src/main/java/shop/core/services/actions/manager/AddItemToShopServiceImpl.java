package shop.core.services.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.domain.item.ItemConverter;
import shop.core.services.validators.services_validators.manager.AddItemToShopValidator;
import shop.core_api.entry_point.manager.AddItemToShopService;
import shop.core_api.requests.manager.AddItemToShopRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.AddItemToShopResponse;

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
        Item item = ItemConverter.toItem(request.getItemDTO());
        itemRepository.save(item);
        return new AddItemToShopResponse();
    }

}
