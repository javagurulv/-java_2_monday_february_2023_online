package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmItemRepositoryImpl implements ItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Item save(Item item) {
        sessionFactory.getCurrentSession().persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long itemId) {
        return Optional.of(sessionFactory.getCurrentSession().get(Item.class, itemId));
    }

    @Override
    public Optional<Item> findByName(String name) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE name = :name", Item.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    //TODO all 3 needed ???
    @Override
    public void changeName(Long id, String newName) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null) {
            item.setName(newName);
            sessionFactory.getCurrentSession().merge(item);
        }
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null) {
            item.setPrice(newPrice);
            sessionFactory.getCurrentSession().merge(item);
        }
    }

    @Override
    public void changeAvailableQuantity(Long id, Integer newAvailableQuantity) {
        Item item = sessionFactory.getCurrentSession().get(Item.class, id);
        if (item != null) {
            item.setAvailableQuantity(newAvailableQuantity);
            sessionFactory.getCurrentSession().merge(item);
        }
    }

    @Override
    public List<Item> getAllItems() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, String ordering, String paging) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name " + ordering, Item.class);
        query.setParameter("name", "%" + itemName + "%");
        addPaging(query, paging);
        return query.getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price, String ordering, String paging) {
        Query<Item> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT i FROM Item i WHERE LOWER(name) LIKE :name AND price <= :price " + ordering, Item.class);
        query.setParameter("name", "%" + itemName + "%");
        query.setParameter("price", price);
        addPaging(query, paging);
        return query.getResultList();
    }

    private void addPaging(Query<Item> query, String paging) {
        //TODO (╯°□°)╯︵ ┻━┻
        if (!paging.isBlank()) {
            query.setMaxResults(Integer.parseInt(paging.split(" ")[1]));
            if (paging.contains("OFFSET")) {
                query.setFirstResult(Integer.parseInt(paging.split(" ")[3]));
            }
        }
    }

}
