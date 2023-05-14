package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcItemDatabaseImpl implements ItemDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO item (name, price, available_quantity) VALUES (?, ?, ?);";
        Object[] args = new Object[]{item.getName(), item.getPrice(), item.getAvailableQuantity()};
        jdbcTemplate.update(sql, args);
        //TODO get ID
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
//        return shopItems.stream()
//                .filter(item -> item.getId().equals(itemId))
//                .findFirst();
        return null;
    }

    @Override
    public Optional<Item> findByName(String name) {
//        return shopItems.stream()
//                .filter(item -> item.getName().equals(name))
//                .findFirst();
        return null;
    }

    @Override
    public void changeName(Long id, String newName) {
//        shopItems.stream()
//                .filter(shopItem -> shopItem.getId().equals(id))
//                .findFirst()
//                .ifPresent(shopItem -> shopItem.setName(newName));
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
//        shopItems.stream()
//                .filter(shopItem -> shopItem.getId().equals(id))
//                .findFirst()
//                .ifPresent(shopItem -> shopItem.setPrice(newPrice));
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
//        shopItems.stream()
//                .filter(shopItem -> shopItem.getId().equals(id))
//                .findFirst()
//                .ifPresent(shopItem -> shopItem.setAvailableQuantity(newAvailableQuantity));
    }

    @Override
    public List<Item> getAllItems() {
//        return shopItems;
        return null;
    }

    @Override
    public List<Item> searchByName(String itemName) {
//        return shopItems.stream()
//                .filter(item -> item.getName().toLowerCase().contains(itemName.toLowerCase()))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
//        return shopItems.stream()
//                .filter(item -> item.getName().toLowerCase().contains(itemName.toLowerCase()) &&
//                        item.getPrice().compareTo(price) <= 0)
//                .collect(Collectors.toList());
        return null;
    }

}
