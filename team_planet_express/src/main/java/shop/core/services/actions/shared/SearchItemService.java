package shop.core.services.actions.shared;

import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.item_list.OrderingService;
import shop.core.services.item_list.PagingService;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@DIComponent
public class SearchItemService {

    @DIDependency
    private Database database;
    @DIDependency
    private SearchItemValidator validator;
    @DIDependency
    private OrderingService orderingService;
    @DIDependency
    private PagingService pagingService;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;

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
            response = new SearchItemResponse(items, totalFoundItemCount,
                    databaseAccessValidator.getUserById(request.getUserId().getValue()).getUserRole());
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
