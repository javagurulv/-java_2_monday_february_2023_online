package shop.core.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private Database database;

    public User createUser(UserCreationData userCreationData) {
        User createdUser = database.accessUserDatabase()
                .save(new User(userCreationData.getName(), userCreationData.getLoginName(), userCreationData.getPassword(), userCreationData.getUserRole()));
        database.accessCartDatabase().save(new Cart(createdUser.getId()));
        return createdUser;
    }

    public Optional<User> findGuestWithOpenCart() {
        return database.accessUserDatabase().getAllUsers().stream()
                .filter(user -> UserRole.GUEST.equals(user.getUserRole()))
                .filter(user -> database.accessCartDatabase().findOpenCartForUserId(user.getId()).isPresent())
                .findFirst();
    }

}
