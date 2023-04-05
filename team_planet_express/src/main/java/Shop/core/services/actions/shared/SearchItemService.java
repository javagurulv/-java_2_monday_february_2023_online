package Shop.core.services.actions.shared;

import Shop.core.database.Database;
import Shop.core.domain.item.Item;
import Shop.core.requests.shared.SearchItemRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.shared.SearchItemResponse;
import Shop.core.services.item.ordering.OrderingService;
import Shop.core.services.item.paging.PagingService;
import Shop.core.services.validators.actions.shared.SearchItemValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SearchItemService {

    private final Database database;
    private final SearchItemValidator validator;
    private final OrderingService orderingService;
    private final PagingService pagingService;

    public SearchItemService(Database database, SearchItemValidator validator, OrderingService orderingService, PagingService pagingService) {
        this.database = database;
        this.validator = validator;
        this.orderingService = orderingService;
        this.pagingService = pagingService;
    }

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            List<Item> items = search(request);
            Integer totalFoundItemCount = items.size();
            items = orderingService.getOrderedItems(items, request.getOrderingRules());
            items = pagingService.getPage(items, request.getPagingRule());
            response = new SearchItemResponse(items, totalFoundItemCount);
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = database.accessItemDatabase().searchByName(request.getItemName());
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = database.accessItemDatabase().searchByNameAndPrice(request.getItemName(), price);
        } else {
            items = database.accessItemDatabase().getAllItems();
        }
        return items;
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }

}