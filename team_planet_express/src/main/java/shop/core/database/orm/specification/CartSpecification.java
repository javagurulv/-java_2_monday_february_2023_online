package shop.core.database.orm.specification;

import org.springframework.data.jpa.domain.Specification;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.cart.Cart_;
import shop.core.domain.user.User;

public interface CartSpecification {

    static Specification<Cart> isCartOpen() {
        return (root, query, builder) -> builder.equal(root.get(Cart_.status), CartStatus.OPEN);
    }

    static Specification<Cart> isEqualUser(User user) {
        return (root, query, builder) -> builder.equal(root.get("user"), user);
    }

}