package shop.console_ui.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

@Component
public class SignUpUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign up";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST);

    private static final String PROMPT_TOPIC_NAME = "your name: ";
    private static final String PROMPT_TOPIC_LOGIN = "your login name: ";
    private static final String PROMPT_TOPIC_PASSWORD = "your password: ";
    private static final String MESSAGE_USER_CREATED = "Welcome to the Planet Express crew, %s!";

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private UserCommunication userCommunication;

    public SignUpUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        String name = userCommunication.requestInput(PROMPT_TOPIC_NAME);
        String login = userCommunication.requestInput(PROMPT_TOPIC_LOGIN);
        String password = userCommunication.requestInput(PROMPT_TOPIC_PASSWORD);
        SignUpRequest request = new SignUpRequest(currentUserId, name, login, password);
        SignUpResponse response = signUpService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_USER_CREATED.formatted(response.getUser().getName()));
        }
    }

}
