package shop.core.services.user;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.Optional;

public class UserService {

    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public User createUser(UserRecord userRecord) {
        User createdUser = database.accessUserDatabase()
                .save(new User(userRecord.name(), userRecord.loginName(), userRecord.password(), userRecord.userRole()));
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