package shop.core.support;

import lombok.Data;
import org.springframework.stereotype.Component;
import shop.core.domain.user.User;

@Data
@Component
public class CurrentUser {

    private User user;

}
