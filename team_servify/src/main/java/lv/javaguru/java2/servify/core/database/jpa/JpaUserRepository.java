package lv.javaguru.java2.servify.core.database.jpa;

import lv.javaguru.java2.servify.core.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
