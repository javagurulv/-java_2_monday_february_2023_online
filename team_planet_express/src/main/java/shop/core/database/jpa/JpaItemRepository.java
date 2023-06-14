package shop.core.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.core.domain.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);

    Optional<Item> findOneByName(String name);

    @Modifying
    @Query("UPDATE Item i SET i.name = :new_name WHERE i.id = :id")
    void updateName(@Param(value = "id") Long id, @Param(value = "new_name") String newName);

    @Modifying
    @Query("UPDATE Item i SET i.price = :new_price WHERE i.id = :id")
    void updatePrice(@Param(value = "id") Long id, @Param(value = "new_price") BigDecimal newPrice);

    @Modifying
    @Query("UPDATE Item i SET i.availableQuantity = :new_available_quantity WHERE i.id = :id")
    void updateAvailableQuantity(@Param(value = "id") Long id, @Param(value = "new_available_quantity") Integer newAvailableQuantity);

    //TODO SEARCH ????
    //List<Item> findByName(String itemName, String ordering, String paging);

    //List<Item> findByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging);

}
