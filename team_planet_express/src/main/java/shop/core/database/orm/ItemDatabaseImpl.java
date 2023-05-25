package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ItemDatabaseImpl implements ItemDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Item save(Item item) {
        sessionFactory.getCurrentSession().persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, itemId);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> findByName(String name) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE name = :name", Item.class);
        query.setParameter("name", name);
        return query.uniqueResultOptional();
    }

    @Override
    public void changeName(Long id, String newName) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null)
            item.setName(newName);
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null)
            item.setPrice(newPrice);
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null)
            item.setAvailableQuantity(newAvailableQuantity);
    }

    @Override
    public List<Item> getAllItems() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name", Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price", Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, String ordering, String paging) {
        String queryString = "SELECT i FROM Item i WHERE LOWER(name) LIKE :name" + ordering + paging;
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery(queryString, Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging) {
        String queryString = "SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price" + ordering + paging;
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery(queryString, Item.class);
        query.setParameter("name", "%%%s%%".formatted(itemName.toLowerCase()));
        query.setParameter("price", price);
        return query.getResultList();
    }
}
