package shop.core.database.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.core.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    List<User> findByLogin(String login);

    Optional<User> findFirstByLogin(String login);

}
