package core.database;

import core.domain.item.Item;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class InMemoryItemDatabaseImpl implements ItemDatabase {

    private Long nextId = 1L;
    private final List<Item> shopItems = new ArrayList<>();

    @Override
    public void save(Item item) {
        item.setId(nextId);
        nextId++;
        shopItems.add(item);
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return shopItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

    @Override
    public Optional<Item> findByName(String name) {
        return shopItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    @Override
    public void changeName(Long id, String newName) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setName(newName));
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setPrice(newPrice));
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        shopItems.stream()
                .filter(shopItem -> shopItem.getId().equals(id))
                .findFirst()
                .ifPresent(shopItem -> shopItem.setAvailableQuantity(newAvailableQuantity));
    }

    @Override
    public List<Item> getAllItems() {
        return shopItems;
    }

    @Override
    public List<Item> searchByName(String itemNameToSearch) {
        return shopItems.stream()
                .filter(item -> item.getName().contains(itemNameToSearch))
                //TODO is it really necessary?
                //.filter(item -> item.getName().contains(itemNameToSearch.subSequence(0, itemNameToSearch.length())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemNameToSearch, BigDecimal priceToSearch) {
        return shopItems.stream()
                .filter(item -> item.getName().contains(itemNameToSearch) &&
                        //TODO sus subsequence
                        //.filter(item -> item.getName().contains(itemNameToSearch.subSequence(0, itemNameToSearch.length())) &&
                        item.getPrice().equals(priceToSearch))
                .collect(Collectors.toList());
    }

}
