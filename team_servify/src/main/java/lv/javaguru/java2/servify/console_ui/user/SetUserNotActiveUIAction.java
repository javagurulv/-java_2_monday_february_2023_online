package lv.javaguru.java2.servify.console_ui.user;
//
//import lv.javaguru.java2.servify.console_ui.UIAction;
//import lv.javaguru.java2.servify.core.dto.requests.SetUserNotActiveRequest;
//import lv.javaguru.java2.servify.core.dto.responses.SetUserNotActiveResponse;
////import lv.javaguru.java2.servify.core.services.users.SetUserNotActiveService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.Scanner;
//
//@Component
//public class SetUserNotActiveUIAction implements UIAction {
//    @Autowired private SetUserNotActiveService setUserNotActiveService;
//
//    @Override
//    public void execute() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter User id to remove: ");
//        Long userId = Long.parseLong(input.nextLine());
//        SetUserNotActiveRequest request = new SetUserNotActiveRequest(userId);
//        SetUserNotActiveResponse response = setUserNotActiveService.execute(request);
//
//        if (response.hasErrors()) {
//            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
//        } else {
//            if (response.isUserInactivated()) {
//                System.out.println("User is deactivated.");
//            } else {
//                System.out.println("User is not deactivated.");
//            }
//        }
//    }
//
//    @Override
//    public String getMenuItem() {
//        return "Remove user";
//    }
//}