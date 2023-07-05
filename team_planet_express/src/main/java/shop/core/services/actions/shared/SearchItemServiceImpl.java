package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.database.specifications.ItemSpecs;
import shop.core.domain.item.Item;
import shop.core.services.validators.services_validators.shared.SearchItemValidator;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;
import shop.core_api.entry_point.shared.SearchItemService;
import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.shared.SearchItemResponse;

import java.util.List;

@Service
@Transactional
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SearchItemValidator validator;

    public SearchItemResponse execute(SearchItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        SearchItemResponse response;
        if (!errors.isEmpty()) {
            response = new SearchItemResponse(errors);
        } else {
            Page<Item> items = search(request);
            boolean nextPageAvailable = items.hasNext();
            response = new SearchItemResponse(items.getContent(), nextPageAvailable);
        }
        return response;
    }

    private Page<Item> search(SearchItemRequest request) {
        Page<Item> items;

        Specification<Item> itemSpecification = ItemSpecs.searchByName(request.getItemName())
                .and(ItemSpecs.searchByPrice(request.getMinPrice(), request.getMaxPrice()));
        List<OrderingRule> orderingRules = request.getOrderingRules();
        if (orderingRules != null && !orderingRules.isEmpty()) {
            itemSpecification = itemSpecification.and(ItemSpecs.orderBy(orderingRules));
        }
        PagingRule pagingRule = request.getPagingRule();
        Pageable page = PageRequest.of(pagingRule.getPageNumber(), pagingRule.getPageSize());
        items = itemRepository.findAll(itemSpecification, page);

        return items;
    }

}
