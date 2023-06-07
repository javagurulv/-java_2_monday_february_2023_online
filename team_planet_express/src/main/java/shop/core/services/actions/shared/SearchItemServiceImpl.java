package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.item_list.OrderingService;
import shop.core.services.item_list.PagingService;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core_api.entry_point.shared.SearchItemService;
import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.shared.SearchItemResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SearchItemValidator validator;
    @Autowired
    private OrderingService orderingService;
    @Autowired
    private PagingService pagingService;

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            List<Item> items = search(request);
            boolean nextPageAvailable = isExtraItemAvailable(request, items);
            response = new SearchItemResponse(items, nextPageAvailable);
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = itemRepository.searchByName(
                    request.getItemName().toLowerCase(),
                    orderingService.getSQLOrderBy(request.getOrderingRules()),
                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = itemRepository.searchByNameAndPrice(
                    request.getItemName().toLowerCase(),
                    price,
                    orderingService.getSQLOrderBy(request.getOrderingRules()),
                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else {
            items = itemRepository.getAllItems();
        }
        return items;
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
