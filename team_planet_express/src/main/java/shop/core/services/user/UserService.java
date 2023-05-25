package shop.core.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.Optional;

@Component
@Transactional
public class UserService {

    @Autowired
    private Repository repository;

    public User createUser(UserCreationData userCreationData) {
        User createdUser = repository.accessUserRepository()
                .save(new User(userCreationData.getName(), userCreationData.getLoginName(), userCreationData.getPassword(), userCreationData.getUserRole()));
        repository.accessCartRepository().save(new Cart(createdUser));
        return createdUser;
    }

    public Optional<User> findGuestWithOpenCart() {
        return repository.accessUserRepository().getAllUsers().stream()
                .filter(user -> UserRole.GUEST.equals(user.getUserRole()))
                .filter(user -> repository.accessCartRepository().findOpenCartForUserId(user).isPresent())
                .findFirst();
    }

}
