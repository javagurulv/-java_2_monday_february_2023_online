package lv.fitness_app.core.database.jpa;

import lv.fitness_app.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, String> {

    void add(User user);
    void deleteUser(User user);
    User findUserByEmail(String email);
}
