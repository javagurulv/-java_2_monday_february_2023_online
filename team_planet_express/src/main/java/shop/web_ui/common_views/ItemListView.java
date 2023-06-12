package shop.web_ui.common_views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.entry_point.customer.GetListShopItemsService;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.GetListShopItemsResponse;
import shop.web_ui.components.MainLayout;
import shop.web_ui.components.item_card.ItemCardBuilder;
import shop.web_ui.components.notification.ErrorMessage;

import java.util.List;

@PageTitle("Main")
@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class ItemListView extends Main {


    public ItemListView(@Autowired GetListShopItemsService getListShopItemsService) {
        GetListShopItemsResponse response = getListShopItemsService.execute(null);
        if (response.hasErrors()) {
            for (CoreError error : response.getErrors()) {
                add(new ErrorMessage(error.getMessage()));
            }
        } else {
            List<ItemDTO> items = response.getShopItemsDTO();
            add(createList(items, 4));
        }
    }

    private VerticalLayout createList(List<ItemDTO> items, int column) {
        VerticalLayout itemList = new VerticalLayout();
        ItemCardBuilder builder = new ItemCardBuilder();
        builder.setWidth(100 / column + "%");

        int counter = 0;
        HorizontalLayout row = new HorizontalLayout();
        row.setAlignItems(FlexComponent.Alignment.CENTER);
        row.setWidthFull();
        for (ItemDTO item : items) {
            Component itemCard = builder.setItemIfoContent(item).build();
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