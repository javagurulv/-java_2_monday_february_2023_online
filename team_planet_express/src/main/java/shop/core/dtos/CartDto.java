package shop.core.dtos;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CartDto {

    String userLogin;
    String cartStatus;
    LocalDateTime lastUpdate;

}
