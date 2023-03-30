package console_ui.actions.shared;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.shared.SearchItemRequest;
import core.responses.shared.SearchItemResponse;
import core.services.actions.shared.SearchItemService;

public class SearchItemUIAction extends UIAction {
    private static final String ACTION_NAME = "Search item in the shop";

    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.ALLUSERS);

    private static final String PROMPT_TOPIC_ITEM = "item name for search: ";
    private static final String PROMPT_TOPIC_PRICE = "item maximum price: ";


    private static final String MESSAGE_SEARCH_SUCCESS = "Search results:";
    private static final String MESSAGE_NOT_FOUND = "No items matched search parameters.";


    private final SearchItemService searchItemService;
    private final UserCommunication userCommunication;

    public SearchItemUIAction(SearchItemService searchItemService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.searchItemService = searchItemService;
        this.userCommunication = userCommunication;
    }


    @Override
    public void execute() {
        userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        String itemName = userCommunication.getInput();
        userCommunication.requestInput(PROMPT_TOPIC_PRICE);
        String price = userCommunication.getInput();

        SearchItemRequest request = new SearchItemRequest(itemName, price);
        SearchItemResponse response = searchItemService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
        } else if (response.getItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_NOT_FOUND);
        } else {
            userCommunication.informUser(MESSAGE_SEARCH_SUCCESS);
            response.getItems().forEach(item -> userCommunication.informUser(item.toString()));
        }


    }

}