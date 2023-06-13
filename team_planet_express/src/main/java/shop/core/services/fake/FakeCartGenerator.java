package shop.core.services.fake;

import shop.core.domain.Cart;
import shop.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public class FakeCartGenerator {

    public List<Cart> createCartsForUsers(List<User> users) {
        List<Cart> fakeCarts = new ArrayList<>();
        for (User user : users) {
            fakeCarts.add(new Cart(user));
        }
        return fakeCarts;
    }

}
