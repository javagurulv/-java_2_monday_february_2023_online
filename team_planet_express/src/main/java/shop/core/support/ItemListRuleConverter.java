package shop.core.support;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import shop.core.support.paging.PagingRule;

@Component
public class ItemListRuleConverter {

    public Pageable getPageRequest(PagingRule pagingRule) {
        return PageRequest.of(pagingRule.getPageNumber(), Integer.parseInt(pagingRule.getPageSize()));
    }

}
