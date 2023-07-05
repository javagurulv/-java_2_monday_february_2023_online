package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core_api.requests.manager.AddItemToShopRequest;

public class AddItemToShopRequestMatcher implements ArgumentMatcher<AddItemToShopRequest> {

    private final String itemName;
    private final String price;
    private final String availableQuantity;

    public AddItemToShopRequestMatcher(String itemName, String price, String availableQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean matches(AddItemToShopRequest request) {
        return itemName.equals(request.getItemDTO().getName()) &&
                price.equals(request.getItemDTO().getPrice()) &&
                availableQuantity.equals(request.getItemDTO().getAvailableQuantity());
    }

}
