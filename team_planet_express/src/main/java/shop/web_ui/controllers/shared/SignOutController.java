package shop.web_ui.controllers.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;

@Controller
public class SignOutController {

    @Autowired
    private SignOutService signOutService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/signOut")
    public String showSignOutPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SignOutRequest());
        return "shared/signOut";
    }

    @PostMapping(value = "/signOut")
    public String processSignOutRequest(
            @ModelAttribute(value = "request") SignOutRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        SignOutResponse response = signOutService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "shared/signOut";
        }
        return "redirect:/";
    }

}
