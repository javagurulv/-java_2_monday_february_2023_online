package lv.javaguru.java2.servify.core.database.jpa;

import lv.javaguru.java2.servify.core.domain.Order;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByUser(UserEntity user);
}
