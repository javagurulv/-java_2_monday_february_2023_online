package shop.web_ui.common_views;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.item.Item;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.web_ui.components.ItemListComponent;
import shop.web_ui.components.MainLayout;

import java.util.List;

@PageTitle("Main")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class ItemListView extends Main {

    public ItemListView(@Autowired ListShopItemsService listShopItemsService) {
        ListShopItemsResponse response = listShopItemsService.execute(null);
        List<Item> items = response.getShopItems();
        add(new ItemListComponent(items));
    }

}
