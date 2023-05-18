package shop.core.services.item_list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.core.domain.item.Item;
import shop.core.support.paging.PagingRule;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PagingService {
    @Value("${paging.enabled}")
    private boolean pagingEnabled;

    public List<Item> getPage(List<Item> items, PagingRule pagingRule) {
        if (pagingEnabled) {
            if (pagingRule != null) {
                long pageSize = Long.parseLong(pagingRule.getPageSize());
                long itemCountToSkip = (pagingRule.getPageNumber() - 1) * pageSize;
                items = items.stream()
                        .skip(itemCountToSkip)
                        .limit(pageSize)
                        .collect(Collectors.toList());
            }
        }
        return items;
    }

    public String getSQLLimitOffset(PagingRule pagingRule) {
        StringBuilder limitOffset = new StringBuilder();
        if (pagingEnabled) {
            if (pagingRule != null) {
                int pageSize = Integer.parseInt(pagingRule.getPageSize());
                limitOffset.append("LIMIT ").append(pageSize + 1);
                if (pagingRule.getPageNumber() > 1) {
                    limitOffset.append(" OFFSET ").append((pagingRule.getPageNumber() - 1) * pageSize);
                }
            }
        }
        return limitOffset.toString();
    }

}
