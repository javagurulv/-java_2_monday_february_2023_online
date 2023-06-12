package lv.javaguru.java2.servify.web_ui.controllers.rest;

import lv.javaguru.java2.servify.core.dto.requests.GetDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersResponse;
import lv.javaguru.java2.servify.core.dto.responses.GetDetailResponse;
import lv.javaguru.java2.servify.core.services.GetAllOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired private GetAllOrdersService allOrdersService;
    @GetMapping(path = "/{id}", produces = "application/json")
    public GetAllOrdersResponse getDetail(@PathVariable Long id) {
        GetDetailRequest request = new GetDetailRequest(id);
        return allOrdersService.getAll();
    }
}
