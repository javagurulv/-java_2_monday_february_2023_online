package shop.core.database;

import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    Optional<Item> findById(Long itemId);

    Optional<Item> findByName(String name);

    void changeName(Long id, String newName);

    void changePrice(Long id, BigDecimal newPrice);

    void changeAvailableQuantity(Long id, Integer newAvailableQuantity);

    List<Item> getAllItems();

    List<Item> searchByName(String itemName, String ordering, String paging);

    List<Item> searchByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging);

}
