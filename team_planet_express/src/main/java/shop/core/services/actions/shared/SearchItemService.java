package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.item_list.OrderingService;
import shop.core.services.item_list.PagingService;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class SearchItemService {

    @Autowired
    private Database database;
    @Autowired
    private SearchItemValidator validator;
    @Autowired
    private OrderingService orderingService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            List<Item> items = search(request);
            //TODO this ain't total anymore
            Integer totalFoundItemCount = items.size();
            response = new SearchItemResponse(items, totalFoundItemCount,
                    databaseAccessValidator.getUserById(request.getUserId().getValue()).getUserRole());
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = database.accessItemDatabase().searchByName(
                    request.getItemName().toLowerCase(),
                    orderingService.getSQLOrderBy(request.getOrderingRules()),
                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = database.accessItemDatabase().searchByNameAndPrice(
                    request.getItemName().toLowerCase(),
                    price,
                    orderingService.getSQLOrderBy(request.getOrderingRules()),
                    pagingService.getSQLLimitOffset(request.getPagingRule()));
        } else {
            items = database.accessItemDatabase().getAllItems();
        }
        return items;
    }

    private boolean isPresent(String value) {
        return value != null && !value.isBlank();
    }

}
