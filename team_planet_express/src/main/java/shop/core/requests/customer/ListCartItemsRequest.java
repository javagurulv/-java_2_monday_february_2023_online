package shop.core.requests.customer;

import lombok.Value;
import shop.core.support.CurrentUser;

@Value
public class ListCartItemsRequest {

    CurrentUser userId;

}
