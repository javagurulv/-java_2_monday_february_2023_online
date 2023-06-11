package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.domain.item.ItemConverter;
import shop.core.services.validators.actions.customer.GetItemValidator;
import shop.core_api.entry_point.customer.GetItemService;
import shop.core_api.requests.customer.GetItemRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.GetItemResponse;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GetItemServiceImpl implements GetItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private GetItemValidator validator;

    public GetItemResponse execute(GetItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        GetItemResponse response;
        if (!errors.isEmpty()) {
            response = new GetItemResponse(errors);
        } else {
            Optional<Item> optionalItem;
            if (request.getItemDTO().getName() != null)
                optionalItem = itemRepository.findByName(request.getItemDTO().getName());
            else
                optionalItem = itemRepository.findById(request.getItemDTO().getId());
            if (optionalItem.isEmpty())
                response = new GetItemResponse(errors);
            else {
                Item item = optionalItem.get();
                response = new GetItemResponse(ItemConverter.toItemDTO(item));

            }
        }
        return response;
    }

}
