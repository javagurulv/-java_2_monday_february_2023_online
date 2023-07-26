package lv.javaguru.java2.library.web_ui.controllers;

import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.requests.SearchReadersAndBooksRequest;
import lv.javaguru.java2.library.core.requests.SearchReadersRequest;
import lv.javaguru.java2.library.core.requests.TakeBookRequest;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;
import lv.javaguru.java2.library.core.responses.SearchReadersResponse;
import lv.javaguru.java2.library.core.responses.TakeBookResponse;
import lv.javaguru.java2.library.core.services.SearchBooksService;
import lv.javaguru.java2.library.core.services.SearchReadersService;
import lv.javaguru.java2.library.core.services.TakeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TakeBookByReaderController {

	@Autowired private TakeBookService takeBookService;

	@Autowired private SearchReadersService searchReadersService;
	@Autowired private SearchBooksService searchBooksService;

	@GetMapping(value = "/takeBookByReader")
	public String showTakeBookByReaderPage(ModelMap modelMap) {
		modelMap.addAttribute("request", new TakeBookRequest());
		modelMap.addAttribute("searchRequest", new SearchReadersAndBooksRequest());
		return "takeBookByReader";
	}

	@PostMapping("/takeBookByReader")
	public String processTakeBookByReaderRequest(@ModelAttribute(value = "request") TakeBookRequest request, ModelMap modelMap) {
		TakeBookResponse response = takeBookService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrors());
			return "takeBookByReader";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/takeBookByReader/search")
	public String processSearchReadersRequest(@ModelAttribute(value = "searchRequest") SearchReadersAndBooksRequest searchRequest,
											  ModelMap modelMap) {
		SearchReadersRequest searchReaderRequest = new SearchReadersRequest(
				searchRequest.getReaderFirstName(), searchRequest.getReaderLastName()
		);
		SearchReadersResponse searchReadersResponse = searchReadersService.execute(searchReaderRequest);
		modelMap.addAttribute("readers", searchReadersResponse.getReaders());

		SearchBooksRequest searchBooksRequest = new SearchBooksRequest(
				searchRequest.getBookTitle(), searchRequest.getBookAuthor()
		);
		SearchBooksResponse searchBooksResponse = searchBooksService.execute(searchBooksRequest);
		modelMap.addAttribute("books", searchBooksResponse.getBooks());

		modelMap.addAttribute("request", new TakeBookRequest());

		return "takeBookByReader";
	}

}
