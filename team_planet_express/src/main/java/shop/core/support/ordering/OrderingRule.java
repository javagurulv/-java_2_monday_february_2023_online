package shop.core.support.ordering;

import jakarta.persistence.metamodel.SingularAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderingRule {

    private SingularAttribute orderBy;
    private Boolean ascending;

    public Boolean isAscending() {
        return ascending;
    }
}
