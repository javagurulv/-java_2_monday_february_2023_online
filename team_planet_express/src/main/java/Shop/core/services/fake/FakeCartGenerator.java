package Shop.core.services.fake;

import Shop.core.domain.cart.Cart;
import Shop.core.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class FakeCartGenerator {

    public List<Cart> createCartsForUsers(List<User> users) {
        List<Cart> fakeCarts = new ArrayList<>();
        for (User user : users) {
            fakeCarts.add(new Cart(user.getId()));
        }
        return fakeCarts;
    }

}
