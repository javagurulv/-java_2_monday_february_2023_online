package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.ItemConverter;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.item_list.OrderingService;
import shop.core.services.item_list.PagingService;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Transactional
public class SearchItemService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private SearchItemValidator validator;
    @Autowired
    private OrderingService orderingService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;
    @Autowired
    private ItemConverter itemConverter;

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            List<Item> items = search(request);
            boolean nextPageAvailable = isExtraItemAvailable(request, items);
            List<ItemDto> itemDtos = itemConverter.toItemDto(items);
            response = new SearchItemResponse(itemDtos, nextPageAvailable);
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        //TODO this is dead
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
//            items = itemRepository.findByName(
//                    request.getItemName().toLowerCase(),
//                    orderingService.getSQLOrderBy(request.getOrderingRules()),
//                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
//            items = itemRepository.findByNameAndPrice(
//                    request.getItemName().toLowerCase(),
//                    price,
//                    orderingService.getSQLOrderBy(request.getOrderingRules()),
//                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else {
            items = itemRepository.findAll();
        }
        //return items;
        return null;
    }

    private boolean isExtraItemAvailable(SearchItemRequest request, List<Item> items) {
        boolean extraItemPresent = request.getPagingRule() != null &&
                items.size() > Integer.parseInt(request.getPagingRule().getPageSize());
        if (extraItemPresent) {
            items.remove(items.size() - 1);
        }
        return extraItemPresent;
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }

}
