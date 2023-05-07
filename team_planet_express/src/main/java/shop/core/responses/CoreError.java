package shop.core.responses;

import lombok.Value;

@Value
public class CoreError {

    String field;
    String errorCode;
    String message;

}
