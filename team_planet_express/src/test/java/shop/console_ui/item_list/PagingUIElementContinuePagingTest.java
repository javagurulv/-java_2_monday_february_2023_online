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
        assertFalse(pagingUIElement.continuePagingThrough(null, 10));
    }

    @Test
    void shouldReturnFalseForPageSizeBiggerThanFoundCount() {
        when(mockPagingRule.getPageSize()).thenReturn("10");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, 5));
    }

    @Test
    void shouldReturnFalseForPageSizeEqualToFoundCount() {
        when(mockPagingRule.getPageSize()).thenReturn("10");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, 10));
    }

    @Test
    void shouldChangePageToNextWhenAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        when(mockUserCommunication.requestInput(anyString())).thenReturn("next");
        assertTrue(pagingUIElement.continuePagingThrough(mockPagingRule, 20));
        verify(mockPagingRule).changePageNumber(1);
    }

    @Test
    void shouldChangePageToPreviousWhenAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(2);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        when(mockUserCommunication.requestInput(anyString())).thenReturn("back");
        assertTrue(pagingUIElement.continuePagingThrough(mockPagingRule, 20));
        verify(mockPagingRule).changePageNumber(-1);
    }

    @Test
    void shouldReturnFalseOnPagingExit() {
        when(mockPagingRule.getPageSize()).thenReturn("10");
        when(mockUserCommunication.requestInput(anyString())).thenReturn("exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, 20));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

    @Test
    void shouldIgnoreNextWhenNotAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(2);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        when(mockUserCommunication.requestInput(anyString())).thenReturn("next", "exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, 5));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

    @Test
    void shouldIgnoreBackWhenNotAvailable() {
        when(mockPagingRule.getPageNumber()).thenReturn(1);
        when(mockPagingRule.getPageSize()).thenReturn("10");
        when(mockUserCommunication.requestInput(anyString())).thenReturn("back", "exit");
        assertFalse(pagingUIElement.continuePagingThrough(mockPagingRule, 20));
        verify(mockPagingRule, times(0)).changePageNumber(anyInt());
    }

}
