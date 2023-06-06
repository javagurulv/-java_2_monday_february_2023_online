package lv.javaguru.java2.servify.console_ui;

//import lv.javaguru.java2.servify.core.services.GetOrdersByUserIdService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Scanner;
//
//@Component
//public class GetOrderByUserIdUIAction implements UIAction {
//    @Autowired
//    private GetOrdersByUserIdService getOrdersByUserIdService;
//
//    @Override
//    public void execute() {
//        var input = new Scanner(System.in);
//        System.out.println("Enter userId");
//        var userId = Long.parseLong(input.nextLine());
//        getOrdersByUserIdService.getOrders(userId).getOrders().forEach(System.out::println);
//        System.out.println("List end");
//    }
//
//    @Override
//    public String getMenuItem() {
//        return "Show orders for userId";
//    }
//}
