package lv.javaguru.java2.library.web_ui.controllers;

import lv.javaguru.java2.library.core.requests.GetAllReaderBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllReaderBooksResponse;
import lv.javaguru.java2.library.core.services.GetAllReaderBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllReaderBooksController {

	@Autowired private GetAllReaderBooksService getAllReaderBooksService;


	@GetMapping(value = "/showAllReaderBooks")
	public String showAllReaderBooks(ModelMap modelMap) {
		GetAllReaderBooksResponse response = getAllReaderBooksService.execute(
				new GetAllReaderBooksRequest()
		);
		modelMap.addAttribute("readerBooks", response.getReaderBooks());
		return "/showAllReaderBooks";
	}

}
