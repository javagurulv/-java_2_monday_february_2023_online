package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemDatabase;
import shop.core.domain.item.Item;
import shop.core.domain.item.Item_;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);

        cr.select(root).where(
                cb.equal(root.get(Item_.name), name)
        );
        return entityManager.createQuery(cr).getResultStream().findFirst();
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);
        return entityManager.createQuery(cr.select(root)).getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, List<OrderingRule> orderingRules, String sqlLimitOffset) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);

        cr.select(root).where(
                cb.like(root.get(Item_.name), "%" + itemName.toLowerCase() + "%")
        );
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);

        cr.select(root).where(
                cb.like(root.get(Item_.name), "%" + itemName.toLowerCase() + "%"),
                cb.equal(root.get(Item_.price), price)
        );
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<Item> searchByName(String itemName, List<OrderingRule> orderingRules, PagingRule pagingRule) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);

        cr.select(root).where(
                cb.like(root.get(Item_.name), "%" + itemName.toLowerCase() + "%")
        );
        for (OrderingRule orderingRule : orderingRules) {
            cr.orderBy(orderingRule.isAscending() ?
                    cb.asc(root.get(orderingRule.getOrderBy())) :
                    cb.desc(root.get(orderingRule.getOrderBy())));
        }

        return entityManager
                .createQuery(cr)
                .setFirstResult((Integer.valueOf(pagingRule.getPageSize()) - 1) * pagingRule.getPageNumber())
                .setMaxResults(Integer.valueOf(pagingRule.getPageSize()) + 1)
                .getResultList();
    }

    @Override
    public List<Item> searchByNameAndPrice(String itemName, BigDecimal price, List<OrderingRule> orderingRules, PagingRule pagingRule) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cr = cb.createQuery(Item.class);
        Root<Item> root = cr.from(Item.class);

        cr.select(root).where(
                cb.like(root.get(Item_.name), "%" + itemName.toLowerCase() + "%"),
                cb.equal(root.get(Item_.price), price)
        );
        for (OrderingRule orderingRule : orderingRules) {
            cr.orderBy(orderingRule.isAscending() ?
                    cb.asc(root.get(orderingRule.getOrderBy())) :
                    cb.desc(root.get(orderingRule.getOrderBy())));
        }

        return entityManager
                .createQuery(cr)
                .setFirstResult((pagingRule.getPageNumber() - 1) * Integer.valueOf(pagingRule.getPageSize()))
                .setMaxResults(Integer.valueOf(pagingRule.getPageSize()) + 1)
                .getResultList();
    }
}
