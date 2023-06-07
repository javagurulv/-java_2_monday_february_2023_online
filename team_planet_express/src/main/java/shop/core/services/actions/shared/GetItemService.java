package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.requests.shared.GetItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.GetItemResponse;
import shop.core.services.validators.actions.shared.GetItemValidator;

import java.util.List;

@Component
@Transactional
public class GetItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private GetItemValidator validator;

    public GetItemResponse execute(GetItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetItemResponse(errors);
        }
        Long itemId = Long.parseLong(request.getItemId());
        return new GetItemResponse(itemRepository.findById(itemId).orElseThrow());
    }

}
