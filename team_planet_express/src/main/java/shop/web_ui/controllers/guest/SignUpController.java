package shop.web_ui.controllers.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/signUp")
    public String showSignUpPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SignUpRequest());
        return "guest/signUp";
    }

    @PostMapping(value = "/signUp")
    public String processSignUpRequest(
            @ModelAttribute(value = "request") SignUpRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        SignUpResponse response = signUpService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "guest/signUp";
        } else {
            return "redirect:/";
        }
    }

}
