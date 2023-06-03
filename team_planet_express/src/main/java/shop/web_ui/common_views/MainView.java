package shop.web_ui.common_views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.item.Item;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;

import java.util.List;
import java.util.Locale;

@PageTitle("Main")
@Route(value = "main")
@AnonymousAllowed
public class MainView extends Main {


    public MainView(@Autowired ListShopItemsService listShopItemsService) {
        ListShopItemsResponse response = listShopItemsService.execute(null);
//        if (response.hasErrors()) {
//            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
//        } else {
//            printCartItems(response);
//        }

        Grid<Item> grid = new Grid<>(Item.class, false);
        grid.addColumn(Item::getName).setHeader("Name");
        grid.addColumn(new NumberRenderer<>(
                Item::getPrice, "$ %(,.2f",
                Locale.US, "$ 0.00")).setHeader("Price");
        grid.addColumn(Item::getAvailableQuantity).setHeader("Available quantity");
        grid.addColumn(createBuyItemRenderer()).setHeader("buy");
//        grid.addColumn(LitRenderer.<Person>of(
//                        "<button @click=\"${handleUpdate}\">Update</button>" +
//                                "<button @click=\"${handleRemove}\">Remove</button>")
//                .withFunction("handleUpdate", person -> {
//                    person.setName(person.getName() + " Updated");
//                    grid.getDataProvider().refreshItem(person);
//                }).withFunction("handleRemove", person -> {
//                    ListDataProvider<Person> dataProvider =
//                            (ListDataProvider<Person>) grid
//                                    .getDataProvider();
//                    dataProvider.getItems().remove(person);
//                    dataProvider.refreshAll();
//                })).setHeader("Actions");

        List<Item> items = response.getShopItems();
        grid.setItems(items);
        //add(grid);
    }

    private Renderer createBuyItemRenderer() {
        return LitRenderer.<Item>of(
                "<button>Update</button>" +
                        "<button>Remove</button>");
    }

}