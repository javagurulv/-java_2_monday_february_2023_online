package lv.javaguru.java2.servify.web_ui.controllers.rest;

import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.GetDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.AddDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.GetDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import lv.javaguru.java2.servify.core.services.details.AddDetailService;
import lv.javaguru.java2.servify.core.services.details.GetDetailService;
import lv.javaguru.java2.servify.core.services.details.RemoveDetailService;
import lv.javaguru.java2.servify.core.services.details.UpdateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detail")
public class DetailRestController {
    @Autowired
    private GetDetailService getDetailService;
    @Autowired
    private AddDetailService addDetailService;
    @Autowired
    private UpdateDetailService updateDetailService;
    @Autowired
    private RemoveDetailService removeDetailService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetDetailResponse getDetail(@PathVariable Long id) {
        GetDetailRequest request = new GetDetailRequest(id);
        return getDetailService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddDetailResponse addDetail(@RequestBody AddDetailRequest request) {
        return addDetailService.execute(request);
    }
    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateDetailResponse updateDetail(@RequestBody UpdateDetailRequest request) {
        return updateDetailService.update(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public RemoveDetailResponse deleteDetail(@PathVariable Long id) {
        RemoveDetailRequest request = new RemoveDetailRequest(id);
        return removeDetailService.execute(request);
    }

}
