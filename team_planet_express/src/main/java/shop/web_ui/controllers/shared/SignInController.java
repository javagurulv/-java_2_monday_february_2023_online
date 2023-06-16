package shop.web_ui.controllers.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.shared.SignInResponse;
import shop.core.services.actions.shared.SignInService;
import shop.core.support.CurrentUserId;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/signIn")
    public String showSignInPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SignInRequest());
        return "shared/signIn";
    }

    @PostMapping(value = "/signIn")
    public String processSignInRequest(
            @ModelAttribute(value = "request") SignInRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        SignInResponse response = signInService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("signInErrors", response.getErrors());
            return "shared/signIn";
        }
        return "redirect:/";
    }

}
