package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OrmItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.ofNullable(entityManager.find(Item.class, itemId));
    }

    @Override
    public Optional<Item> findByName(String name) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE name = :name", Item.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    //TODO all 3 needed ???
    @Override
    public void changeName(Long id, String newName) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            item.setName(newName);
            entityManager.merge(item);
        }
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            item.setPrice(newPrice);
            entityManager.merge(item);
        }
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            item.setAvailableQuantity(newAvailableQuantity);
            entityManager.merge(item);
        }
    }

    @Override
    public List<Item> getAllItems() {
        return entityManager
                .createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, String ordering, String paging) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name " + ordering, Item.class);
        query.setParameter("name", "%" + itemName + "%");
        addPaging(query, paging);
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price " + ordering, Item.class);
        query.setParameter("name", "%" + itemName + "%");
        query.setParameter("price", price);
        addPaging(query, paging);
        return query.getResultList();
    }

    private void addPaging(TypedQuery<Item> query, String paging) {
        //TODO (╯°□°)╯︵ ┻━┻
        if (!paging.isBlank()) {
            query.setMaxResults(Integer.parseInt(paging.split(" ")[1]));
            if (paging.contains("OFFSET")) {
                query.setFirstResult(Integer.parseInt(paging.split(" ")[3]));
            }
        }
    }

}
