package shop.core.services.fake;

import shop.core.domain.User;
import shop.core.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

//TODO YEET
public class FakeUserGenerator {

    public List<User> createUsers() {
        List<User> fakeUsers = new ArrayList<>();
        fakeUsers.add(new User("Guest", "", "", UserRole.GUEST));
        fakeUsers.add(new User("Customer", "customer", "customer", UserRole.CUSTOMER));
        fakeUsers.add(new User("Manager", "manager", "manager", UserRole.MANAGER));
        return fakeUsers;
    }

}
