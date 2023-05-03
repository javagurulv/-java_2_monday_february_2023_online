package lv.javaguru.java2.servify.core.database;

public class Database {
    private UsersDatabase usersDatabase;
    private OrderDatabase orderDatabase;
    private DetailDatabase detailDatabase;


    public Database() {
        this.usersDatabase = new InMemoryUsersDatabaseImpl();
        this.orderDatabase = new InMemoryOrderDatabaseImpl();
        this.detailDatabase = new InMemoryDetailDatabaseImpl();
    }

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
