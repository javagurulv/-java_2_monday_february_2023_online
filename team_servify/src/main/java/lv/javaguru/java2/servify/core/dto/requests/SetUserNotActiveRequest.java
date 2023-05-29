package lv.javaguru.java2.servify.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SetUserNotActiveRequest {
    private Long userIdToSetInactive;
}
