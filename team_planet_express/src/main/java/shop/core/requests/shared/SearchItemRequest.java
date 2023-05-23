package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUser;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.util.List;

@Value
public class SearchItemRequest {

    CurrentUser currentUser;
    String itemName;
    String price;
    List<OrderingRule> orderingRules;
    PagingRule pagingRule;

}
