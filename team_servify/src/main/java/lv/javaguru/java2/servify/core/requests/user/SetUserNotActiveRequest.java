package lv.javaguru.java2.servify.core.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetUserNotActiveRequest {

    private Long userIdToSetInactive;
}
