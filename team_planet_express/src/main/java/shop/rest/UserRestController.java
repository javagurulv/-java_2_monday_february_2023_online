package shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.core.requests.guest.SignUpRequest;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.guest.SignUpResponse;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.guest.SignUpService;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private SignInService signInService;
    @Autowired
    private SignOutService signOutService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private CurrentUserId currentUserId;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public SignUpResponse addUser(@RequestBody SignUpRequest request) {
        request.setCurrentUserId(currentUserId);
        return signUpService.execute(request);
    }

    @PutMapping(path = "/in", consumes = "application/json", produces = "application/json")
    public SignInResponse signInUser(@RequestBody SignInRequest request) {
        request.setCurrentUserId(currentUserId);
        return signInService.execute(request);
    }

    @PutMapping(path = "/out", produces = "application/json")
    public SignOutResponse signOutUser() {
        SignOutRequest request = new SignOutRequest(currentUserId);
        return signOutService.execute(request);
    }

}
