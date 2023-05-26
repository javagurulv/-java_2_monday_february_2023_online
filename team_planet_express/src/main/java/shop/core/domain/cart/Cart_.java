package shop.core.domain.cart;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import shop.core.domain.user.User;

import java.time.LocalDateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cart.class)
public abstract class Cart_ {

    public static volatile SingularAttribute<Cart, User> user;
    public static volatile SingularAttribute<Cart, CartStatus> status;
    public static volatile SingularAttribute<Cart, LocalDateTime> lastUpdate;
    public static volatile SingularAttribute<Cart, Long> id;
    public static final String ADDRESS = "address";
    public static final String STATUS = "status";
    public static final String LAST_UPDATE = "lastUpdate";
    public static final String ID = "id";

}