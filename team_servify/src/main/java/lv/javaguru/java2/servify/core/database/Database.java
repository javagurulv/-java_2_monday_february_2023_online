package lv.javaguru.java2.servify.core.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Database {
    @Autowired private UsersDatabase usersDatabase;
    @Autowired private OrderDatabase orderDatabase;
    @Autowired private DetailDatabase detailDatabase;

    public UsersDatabase getUsersDatabase() {
        return usersDatabase;
    }

    public OrderDatabase getOrderDatabase() {
        return orderDatabase;
    }

    public DetailDatabase getDetailDatabase() {
        return detailDatabase;
    }
}
