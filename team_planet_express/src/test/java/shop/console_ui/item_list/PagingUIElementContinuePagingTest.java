package shop.console_ui.item_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.support.paging.PagingRule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagingUIElementContinuePagingTest {

    @Mock
    private UserCommunication mockUserCommunication;
    @Mock
    private PagingRule mockPagingRule;

    @InjectMocks
    private PagingUIElement pagingUIElement;

    @Test
    void shouldReturnFalseForNoPagingRule() {
        assertFalse(pagingUIElement.continuePagingThrough(null, false));
    }

    @Test
    void shouldReturnFalseForPageSizeBiggerThanFoundCount() {
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, false));
    }

    @Test
    void shouldReturnFalseForPageSizeEqualToFoundCount() {
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, false));
    }

    @Test
    void shouldChangePageToNextWhenAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockUserCommunication.requestInput(anyString())).thenReturn("next");
        assertTrue(pagingUIElement.continuePagingThrough(mockPagingRule, true));
        verify(mockPagingRule).changePageNumber(1);
    }

    @Test
    void shouldChangePageToPreviousWhenAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(2);
        when(mockUserCommunication.requestInput(anyString())).thenReturn("back");
        assertTrue(pagingUIElement.continuePagingThrough(mockPagingRule, false));
        verify(mockPagingRule).changePageNumber(-1);
    }

    @Test
    void shouldReturnFalseOnPagingExit() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, true));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

    @Test
    void shouldIgnoreNextWhenNotAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(2);
        when(mockUserCommunication.requestInput(anyString())).thenReturn("next", "exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, false));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

    @Test
    void shouldIgnoreBackWhenNotAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockUserCommunication.requestInput(anyString())).thenReturn("back", "exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, true));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

}
