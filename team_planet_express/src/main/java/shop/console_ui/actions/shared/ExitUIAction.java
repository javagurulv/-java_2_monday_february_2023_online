package shop.console_ui.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.enums.UserRole;
import shop.core.services.actions.shared.ExitService;

@Component
public class ExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Exit";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    @Autowired
    private ExitService exitService;
    @Autowired
    private UserCommunication userCommunication;

    public ExitUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        exitService.execute();
    }

}
