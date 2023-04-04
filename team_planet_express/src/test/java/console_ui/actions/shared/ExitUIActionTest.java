package console_ui.actions.shared;

import console_ui.UserCommunication;
import core.services.actions.shared.ExitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExitUIActionTest {

    @Mock private ExitService mockExitService;
    @Mock private UserCommunication mockUserCommunication;

    @InjectMocks private ExitUIAction action;

    @Test
    void shouldPrintExitMessage() {
        action.execute();
        verify(mockUserCommunication).informUser(anyString());
    }

    @Test
    void shouldCallService() {
        action.execute();
        verify(mockExitService).execute();
    }

}
