package shop.core.domain.cart_item;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CartItem.class)
public class CartItem_ {
    public static final String ITEM = "item";
    public static final String CART = "cart";
    public static final String ORDERED_QUANTITY = "orderedQuantity";
    public static final String ID = "id";
    public static volatile SingularAttribute<CartItem, Item> item;
    public static volatile SingularAttribute<CartItem, Cart> cart;
    public static volatile SingularAttribute<CartItem, Integer> orderedQuantity;
    public static volatile SingularAttribute<CartItem, Long> id;

}
