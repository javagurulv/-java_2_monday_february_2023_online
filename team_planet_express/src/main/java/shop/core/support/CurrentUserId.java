package shop.core.support;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Deprecated
public class CurrentUserId {

    private Long value;

}
