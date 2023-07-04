package lv.fitness_app.web_ui.controllers.rest;

import lv.fitness_app.core.requests.SearchExerciseRequest;
import lv.fitness_app.core.responses.SearchExerciseResponse;
import lv.fitness_app.core.services.SearchExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
public class ExerciseRestController {

    @Autowired
    private SearchExerciseService searchExerciseService;

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchExerciseResponse searchExercisePost(@RequestBody SearchExerciseRequest request) {
        return searchExerciseService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchExerciseResponse searchExerciseGet(@RequestParam String name,
                                              @RequestParam String muscleGroup) {
        SearchExerciseRequest request = new SearchExerciseRequest(name, muscleGroup);
        return searchExerciseService.execute(request);
    }
}
