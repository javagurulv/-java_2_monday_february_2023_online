package shop.core.services.actions.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.validators.services_validators.manager.ChangeItemDataValidator;
import shop.core_api.entry_point.manager.ChangeItemDataService;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.ChangeItemDataResponse;

import java.util.List;

@Service
@Transactional
public class ChangeItemDataServiceImpl implements ChangeItemDataService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ChangeItemDataValidator validator;

    public ChangeItemDataResponse execute(ChangeItemDataRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeItemDataResponse(errors);
        }
        Item item = itemRepository.findById(request.getItemDTO().getId()).get();
        if (request.getItemDTO().getName() != null)
            item.setName(request.getItemDTO().getName());
        if (request.getItemDTO().getPrice() != null)
            item.setPrice(request.getItemDTO().getPrice().getAmount());
        if (request.getItemDTO().getAvailableQuantity() != null)
            item.setAvailableQuantity(request.getItemDTO().getAvailableQuantity());
        if (request.getItemDTO().getItemPicture() != null)
            item.setItemPicture(request.getItemDTO().getItemPicture());
        return new ChangeItemDataResponse();
    }


}
