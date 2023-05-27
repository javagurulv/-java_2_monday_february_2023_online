package shop.core.database;

import shop.core.domain.item.Item;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemDatabase {

    Item save(Item item);

    Optional<Item> findById(Long itemId);

    Optional<Item> findByName(String name);

    void changeName(Long id, String newName);

    void changePrice(Long id, BigDecimal newPrice);

    void changeAvailableQuantity(Long id, Integer newAvailableQuantity);

    List<Item> getAllItems();

    List<Item> searchByName(String itemName, List<OrderingRule> orderingRules, String sqlLimitOffset);

    List<Item> searchByNameAndPrice(String itemName, BigDecimal price);

    List<Item> searchByName(String itemName, List<OrderingRule> ordering, PagingRule paging);

    List<Item> searchByNameAndPrice(String itemName, BigDecimal price, List<OrderingRule> orderingRules, PagingRule pagingRule);
}
