package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ItemDatabaseImpl implements ItemDatabase {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        Item item = entityManager.find(Item.class, itemId);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> findByName(String name) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE name = :name", Item.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public void changeName(Long id, String newName) {
        Item item = entityManager.getReference(Item.class, id);
        if (item != null)
            item.setName(newName);
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        Item item = entityManager.getReference(Item.class, id);
        if (item != null)
            item.setPrice(newPrice);
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        Item item = entityManager.getReference(Item.class, id);
        if (item != null)
            item.setAvailableQuantity(newAvailableQuantity);
    }

    @Override
    public List<Item> getAllItems() {
        return entityManager
                .createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name", Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price", Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, String ordering, String paging) {
        String queryString = "SELECT i FROM Item i WHERE LOWER(name) LIKE :name" + ordering + paging;
        TypedQuery<Item> query = entityManager
                .createQuery(queryString, Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging) {
        String queryString = "SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price" + ordering + paging;
        TypedQuery<Item> query = entityManager
                .createQuery(queryString, Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        query.setParameter("price", price);
        return query.getResultList();
    }
}
