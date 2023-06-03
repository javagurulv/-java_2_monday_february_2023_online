package shop.core.requests.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.support.CurrentUserId;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemRequest {

    private CurrentUserId currentUserId;
    private String itemName;
    private String price;
    private List<OrderingRule> orderingRules;
    private PagingRule pagingRule;

}
