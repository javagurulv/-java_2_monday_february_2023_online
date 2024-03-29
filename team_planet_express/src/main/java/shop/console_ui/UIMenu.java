package shop.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.actions.UIAction;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.List;

@Component
public class UIMenu {

    private static final String LOGIN_MESSAGE = "\r\nHello, ";
    private static final String EXCLAMATION = "!";
    private static final String MENU_HEADER = "Program menu:";
    private static final String PROMPT_TOPIC_ACTION = "an action number: ";
    private static final String ERROR_INVALID_ACTION_NUMBER = "Error: Please enter one of the menu numbers.";

    @Autowired
    private UIActionsList uiActionsList;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private UserCommunication userCommunication;
    @Autowired
    private CurrentUserId currentUserId;

    public void startUI() {
        while (true) {
            //TODO get this out, because endless while is bad for testing
            userCommunication.informUser(LOGIN_MESSAGE + getCurrentUserName() + EXCLAMATION);
            userCommunication.informUser(MENU_HEADER);
            List<UIAction> uiActionsListForUserRole = uiActionsList.getUIActionsListForUserRole();
            for (int i = 0; i < uiActionsListForUserRole.size(); i++) {
                userCommunication.informUser(i + 1 + ". " + uiActionsListForUserRole.get(i).getActionName());
            }
            try {
                int userChoice = Integer.parseInt(userCommunication.requestInput(PROMPT_TOPIC_ACTION));
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

    private String getCurrentUserName() {
        return databaseAccessValidator.getUserById(currentUserId.getValue()).getName();
    }

}
