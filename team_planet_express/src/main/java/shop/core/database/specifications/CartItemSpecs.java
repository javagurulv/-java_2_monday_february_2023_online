package shop.core.database.specifications;

import org.springframework.data.jpa.domain.Specification;
import shop.core.domain.cart_item.CartItem;

public interface CartItemSpecs {
    static Specification<CartItem> findBy(String fieldName, Object y) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), y);
    }
}