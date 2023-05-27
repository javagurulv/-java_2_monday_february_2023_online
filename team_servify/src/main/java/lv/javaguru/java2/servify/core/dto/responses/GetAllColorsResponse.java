package lv.javaguru.java2.servify.core.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.Color;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllColorsResponse {
    private List<Color> colors;
}
