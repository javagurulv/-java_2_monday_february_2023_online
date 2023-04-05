package Shop.console_ui.actions.shared;

import Shop.core.domain.user.UserRole;
import Shop.core.services.actions.shared.ExitService;
import Shop.console_ui.UserCommunication;
import Shop.console_ui.actions.UIAction;

public class ExitUIAction extends UIAction {

    private static final String ACTION_NAME = "Exit";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALL_USERS);

    private static final String MESSAGE_EXIT = "Thank you for shopping at Planet Express.";

    private final ExitService exitService;
    private final UserCommunication userCommunication;

    public ExitUIAction(ExitService exitService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.exitService = exitService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(MESSAGE_EXIT);
        exitService.execute();
    }

}