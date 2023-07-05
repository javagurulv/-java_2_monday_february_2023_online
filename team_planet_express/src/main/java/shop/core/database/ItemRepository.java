package shop.core.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import shop.core.domain.item.Item;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    Optional<Item> findByName(String name);
}
