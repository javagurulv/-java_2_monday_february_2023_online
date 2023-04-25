package shop.integration_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import shop.acceptance_tests.ApplicationContextSetup;
import shop.console_ui.UIActionsList;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO put this out of its misery, because it is some sort of an abominable integration test now
class UIActionsListTest {

    static private UIActionsList uiActionsList;
    static private CurrentUserId currentUserId;

    @BeforeAll
    static void setupAppContext() {
        ApplicationContext applicationContext = new ApplicationContextSetup().setupApplicationContext();
        uiActionsList = applicationContext.getBean(UIActionsList.class);
        currentUserId = applicationContext.getBean(CurrentUserId.class);
    }

    @Test
    void shouldReturn9ActionsForNoId() {
        currentUserId.setValue(null);
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn9ActionsForGuest() {
        currentUserId.setValue(1L);
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn8ActionsForCustomer() {
        currentUserId.setValue(2L);
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn6ActionsForManager() {
        currentUserId.setValue(3L);
        assertEquals(6, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn4ActionsForAdmin() {
        currentUserId.setValue(4L);
        assertEquals(4, uiActionsList.getUIActionsListForUserRole().size());
    }

}
