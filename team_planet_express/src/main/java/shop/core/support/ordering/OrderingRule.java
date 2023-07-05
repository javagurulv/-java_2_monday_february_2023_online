package shop.core.support.ordering;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderingRule {

    private final OrderBy orderBy;
    private final Boolean isAscending;

}
