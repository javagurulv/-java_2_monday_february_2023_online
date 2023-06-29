package shop.core.requests.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListShopItemsRequest {

    private List<OrderingRule> orderingRules;
    private PagingRule pagingRule;

}
