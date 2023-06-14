package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.ItemConverter;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;
import shop.core.requests.shared.GetItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.GetItemResponse;
import shop.core.services.validators.actions.shared.GetItemValidator;

import java.util.List;

@Component
@Transactional
public class GetItemService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private GetItemValidator validator;
    @Autowired
    private ItemConverter itemConverter;

    public GetItemResponse execute(GetItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetItemResponse(errors);
        }
        Long itemId = Long.parseLong(request.getItemId());
        Item item = itemRepository.findById(itemId).orElseThrow();
        ItemDto itemDto = itemConverter.toItemDto(item);
        return new GetItemResponse(itemDto);
    }

}
