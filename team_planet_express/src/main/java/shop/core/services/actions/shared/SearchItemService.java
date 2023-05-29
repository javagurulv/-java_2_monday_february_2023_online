package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class SearchItemService {

    @Autowired
    private ItemDatabase itemDatabase;
    @Autowired
    private SearchItemValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            List<Item> items = search(request);
            boolean nextPageAvailable = isExtraItemAvailable(request, items);
            response = new SearchItemResponse(items, nextPageAvailable,
                    databaseAccessValidator.getUserById(request.getUserId().getValue()).getUserRole());
        }
        return response;
    }

    private List<Item> search(SearchItemRequest request) {
        List<Item> items;
        //TODO is blank actually ok in here ?
        if (request.getItemName() != null && !isPresent(request.getPrice())) {
            items = itemDatabase.searchByName(
                    request.getItemName().toLowerCase(),
                    request.getOrderingRules(),
                    request.getPagingRule());
        } else if (request.getItemName() != null && isPresent(request.getPrice())) {
            BigDecimal price = new BigDecimal(request.getPrice()).setScale(2, RoundingMode.HALF_UP);
            items = itemDatabase.searchByNameAndPrice(
                    request.getItemName().toLowerCase(),
                    price,
                    request.getOrderingRules(),
                    request.getPagingRule());
        } else {
            items = itemDatabase.getAllItems();
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
