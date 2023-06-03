package shop.web_ui.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangeUserDataController {

    @GetMapping(value = "/changeUserData")
    public String showChangeUserDataPage() {
        return "admin/changeUserData";
    }

}
