package Shop.core.requests.shared;

import Shop.core.support.ordering.OrderingRule;
import Shop.core.support.paging.PagingRule;
import lombok.Value;

import java.util.List;

@Value
public class SearchItemRequest {

    String itemName;
    String price;
    List<OrderingRule> orderingRules;
    PagingRule pagingRule;

}
