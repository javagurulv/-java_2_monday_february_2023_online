package Shop.console_ui;

import Shop.console_ui.actions.UIAction;

import java.util.List;

public class UIMenu {

    private static final String LOGIN_INFO = "\r\nHello, ";
    private static final String EXCLAMATION = "!";
    private static final String MENU_HEADER = "Program menu:";
    private static final String PROMPT_TOPIC_ACTION = "an action number: ";
    private static final String ERROR_INVALID_ACTION_NUMBER = "Error: Please enter one of the menu numbers.";

    private final UIActionsList uiActionsList;
    private final UserCommunication userCommunication;

    public UIMenu(UIActionsList uiActionsList, UserCommunication userCommunication) {
        this.uiActionsList = uiActionsList;
        this.userCommunication = userCommunication;
    }

    public void startUI() {
        while (true) {
            userCommunication.informUser(LOGIN_INFO + uiActionsList.getCurrentUserName() + EXCLAMATION);
            userCommunication.informUser(MENU_HEADER);
            List<UIAction> uiActionsListForUserRole = uiActionsList.getUIActionsListForUserRole();
            for (int i = 0; i < uiActionsListForUserRole.size(); i++) {
                userCommunication.informUser(i + 1 + ". " + uiActionsListForUserRole.get(i).getActionName());
            }
            userCommunication.requestInput(PROMPT_TOPIC_ACTION);
            try {
                Integer userChoice = userCommunication.getMenuActionNumber();
                if (userChoice > 0 && userChoice < uiActionsListForUserRole.size() + 1) {
                    uiActionsListForUserRole.get(userChoice - 1).execute();
                } else {
                    userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
                }
            } catch (NumberFormatException e) {
                userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
            }
        }
    }

}