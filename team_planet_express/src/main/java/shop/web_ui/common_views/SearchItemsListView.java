package shop.web_ui.common_views;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.item.Item;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.support.paging.PagingRule;
import shop.web_ui.components.ItemListComponent;
import shop.web_ui.components.MainLayout;

import java.util.List;

@PageTitle("Main")
@Route(value = "search", layout = MainLayout.class)
@AnonymousAllowed
public class SearchItemsListView extends Main implements HasUrlParameter<String> {

    @Autowired
    SearchItemService searchItemService;

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        SearchItemRequest searchItemRequest = new SearchItemRequest(parameter, "", List.of(), new PagingRule(1, "10"));
        SearchItemResponse response = searchItemService.execute(searchItemRequest);
        List<Item> items = response.getItems();
        add(new ItemListComponent(items));
    }
}
