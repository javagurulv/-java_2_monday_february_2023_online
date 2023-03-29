package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.shared.SignOutRequest;
import core.responses.shared.SignOutResponse;
import core.services.actions.shared.SignOutService;
import core.support.MutableLong;

public class SignOutUIAction extends UIAction {
    private static final String ACTION_NAME = "Sign out";
    private static final String MESSAGE_SIGNOUT = "Sign out is successful";
    private static final int ACCESS_NUM = UserRole.getAccessNumberExclude(UserRole.GUEST);

    private final SignOutService signOutService;
    private final UserCommunication userCommunication;
    private final MutableLong currentUserId;

    public SignOutUIAction(SignOutService signOutService, MutableLong currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUM);
        this.signOutService = signOutService;
        this.userCommunication = userCommunication;
        this.currentUserId = currentUserId;
    }


    @Override
    public void execute() {
        SignOutRequest request = new SignOutRequest(currentUserId);
        SignOutResponse response = signOutService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
            return;
        }

        userCommunication.informUser(MESSAGE_SIGNOUT);

    }

}
