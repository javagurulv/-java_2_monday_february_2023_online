package shop.core.support.paging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PagingRule {

    private final Integer pageNumber;
    private final Integer pageSize;

}
