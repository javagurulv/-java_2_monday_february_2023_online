package shop.core.domain.user;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public class User_ {
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE = "userRole";
    public static final String ID = "id";
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, UserRole> userRole;
    public static volatile SingularAttribute<User, Long> id;
}
