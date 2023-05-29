package shop.core.support.ordering;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderingRule {

    private String orderBy;
    private Boolean ascending;

    public Boolean isAscending() {
        return ascending;
    }
}
