package shop.core_api.requests.shared;

import lombok.Value;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.util.List;

@Value
public class SearchItemRequest {
    String itemName;
    Integer minPrice;
    Integer maxPrice;
    List<OrderingRule> orderingRules;
    PagingRule pagingRule;

}
