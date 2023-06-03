package shop.core.requests.customer;

import lombok.Value;
import shop.core.domain.user.User;

import java.util.Optional;

@Value
public class ListCartItemsRequest {

    Optional<User> user;

}
