package shop.web_ui.common_views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.item.Item;
import shop.core.services.actions.shared.SearchItemServiceImpl;
import shop.core.support.paging.PagingRule;
import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.shared.SearchItemResponse;
import shop.web_ui.components.MainLayout;
import shop.web_ui.components.item_card.ItemCardBuilder;

import java.util.List;

@PageTitle("Main")
@Route(value = "search", layout = MainLayout.class)
@AnonymousAllowed
public class SearchItemsListView extends Main implements HasUrlParameter<String> {

    @Autowired
    SearchItemServiceImpl searchItemService;

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        SearchItemRequest searchItemRequest = new SearchItemRequest(parameter, "", List.of(), new PagingRule(1, "10"));
        SearchItemResponse response = searchItemService.execute(searchItemRequest);
        List<Item> items = response.getItems();
        add(createList(items, 4));
    }

    private VerticalLayout createList(List<Item> items, int column) {
        VerticalLayout itemList = new VerticalLayout();
        ItemCardBuilder builder = new ItemCardBuilder();

        int counter = 0;
        HorizontalLayout row = new HorizontalLayout();
        row.setAlignItems(FlexComponent.Alignment.CENTER);
        row.setWidthFull();
        for (Item item : items) {
            Component itemCard = builder.setItemIfoContent(item).setWidth(100 / column + "%").build();
            row.add(itemCard);
            counter++;
            if (counter == column) {
                itemList.add(row);
                counter = 0;
                row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);
                row.setWidthFull();
            }
        }
        if (counter > 0) {
            itemList.add(row);
        }
        return itemList;
    }
}
