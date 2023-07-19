package lv.javaguru.java2.library.web_ui.controllers;

import lv.javaguru.java2.library.core.requests.GetAllReadersRequest;
import lv.javaguru.java2.library.core.responses.GetAllReadersResponse;
import lv.javaguru.java2.library.core.services.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReadersController {

	@Autowired private GetAllReadersService getAllReadersService;


	@GetMapping(value = "/showAllReaders")
	public String showAllReaders(ModelMap modelMap) {
		GetAllReadersResponse response = getAllReadersService.execute(
				new GetAllReadersRequest()
		);
		modelMap.addAttribute("readers", response.getReaders());
		return "/showAllReaders";
	}

}
