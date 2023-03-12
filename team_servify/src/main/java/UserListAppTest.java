import storage.ArrayListUserRepository;
import storage.UserRepository;

public class UserListAppTest {

    public static void main(String[] args) {
        UserRepository userRepository = new ArrayListUserRepository();

        while (true) {
            printMenuToConsole();
            int userChoice = getUserMenuChoice();
        }


    }

    private static void printMenuToConsole() {
        System.out.println("Main page:");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Go to Order page");
        System.out.println("4. TEST print all users");
        System.out.println("5. Exit");
    }
    private static int getUserMenuChoice() {

        return 1;
    }
}
