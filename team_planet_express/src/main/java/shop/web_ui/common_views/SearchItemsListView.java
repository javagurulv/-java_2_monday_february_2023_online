package shop.web_ui.common_views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.support.paging.PagingRule;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.entry_point.shared.SearchItemService;
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
    SearchItemService searchItemService;

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        SearchItemRequest searchItemRequest = new SearchItemRequest(parameter, 0, null, List.of(), new PagingRule(0, 10));
        SearchItemResponse response = searchItemService.execute(searchItemRequest);
        List<ItemDTO> items = response.getItemsDTO();
        add(createList(items, 4));
    }

    private VerticalLayout createList(List<ItemDTO> items, int column) {
        VerticalLayout itemList = new VerticalLayout();
        ItemCardBuilder builder = new ItemCardBuilder();

        int counter = 0;
        HorizontalLayout row = new HorizontalLayout();
        row.setAlignItems(FlexComponent.Alignment.CENTER);
        row.setWidthFull();
        for (ItemDTO item : items) {
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
