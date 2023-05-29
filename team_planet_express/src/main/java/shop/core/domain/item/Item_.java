package shop.core.domain.item;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String AVAILABLE_QUANTITY = "availableQuantity";
    public static final String ID = "id";
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, BigDecimal> price;
    public static volatile SingularAttribute<Item, Integer> availableQuantity;
    public static volatile SingularAttribute<Item, Long> id;
}
