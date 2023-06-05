package shop.web_ui.components.item_card;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.customer_views.ItemView;

public class ItemCardBuilder {

    private ItemInfoCard itemInfoCard;
    private Button deleteButton;

    private ImageItemCard image;
    private Text quantity;
    private Item item;

    private String width;

    private Boolean clickable = true;

    public ItemCard build() {
        ItemCard itemCard = new ItemCard();
        image = new ImageItemCard();
        itemCard.add(image, itemInfoCard);
        itemCard.setImage(image);
        itemCard.setItemInfoCard(itemInfoCard);
        if (width != null)
            itemCard.setMinWidth(width);
        if (deleteButton != null)
            itemCard.add(deleteButton);
        if (clickable) {
            long id = item.getId();
            itemCard.addClickListener(event -> itemCard.getUI().ifPresent(ui -> ui.navigate(
                    ItemView.class, id)
            ));
            itemCard.addClassName(LumoUtility.Border.ALL);
            itemCard.getElement().addEventListener("mouseover", event -> {
                itemCard.addClassName(LumoUtility.BorderColor.PRIMARY);
            });
            itemCard.getElement().addEventListener("mouseout", event -> {
                itemCard.removeClassName(LumoUtility.BorderColor.PRIMARY);
            });
        }
        return itemCard;
    }

    public ItemCardBuilder setItemIfoContent(Item item) {
        this.item = item;
        itemInfoCard = new ItemInfoCard(item);
        return this;
    }

    public ItemCardBuilder setItemIfoContent(CartItem cartItem) {
        this.item = cartItem.getItem();
        itemInfoCard = new ItemInfoCard(cartItem);
        return this;
    }

    public ItemCardBuilder setWidth(String width) {
        this.width = width;
        return this;
    }

    public ItemCardBuilder setDelButton(SecurityService securityService, RemoveItemFromCartService removeItemFromCartService) {
        deleteButton = new Button(new Icon(VaadinIcon.TRASH));
        deleteButton.addClickListener(e -> {
            RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(
                    securityService.getAuthenticatedUserFromDB().get(),
                    item.getName()
            );
            removeItemFromCartService.execute(request);
            deleteButton.getParent().get().removeFromParent();
        });
        return this;
    }

    public void updateQuantity(String quantity) {
        this.quantity.setText(quantity);
    }

    public ItemCardBuilder setClickable(Boolean clickable) {
        this.clickable = clickable;
        return this;
    }
}
