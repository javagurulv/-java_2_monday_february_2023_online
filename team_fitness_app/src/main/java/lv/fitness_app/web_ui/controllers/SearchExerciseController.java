package lv.fitness_app.web_ui.controllers;

import lv.fitness_app.core.requests.SearchExerciseRequest;
import lv.fitness_app.core.responses.SearchExerciseResponse;
import lv.fitness_app.core.services.SearchExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchExerciseController {

    @Autowired
    SearchExerciseService searchExerciseService;

    @GetMapping(value = "/searchExercese")
    public String showSearchPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchExerciseRequest());
        return "SearchExercise";
    }

    @PostMapping("/searchExercise")
    public String processSearchExerciseRequest(@ModelAttribute(value = "request") SearchExerciseRequest request, ModelMap modelMap) {
        SearchExerciseResponse response = searchExerciseService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("error", response.getErrors());
        } else {
            modelMap.addAttribute("exercise", response.getExercises());
        }
        return "searchExercise";
    }

}
