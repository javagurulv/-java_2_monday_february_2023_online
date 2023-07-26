package lv.fitness_app.web_ui.controllers;

import com.opencsv.exceptions.CsvException;
import lv.fitness_app.core.requests.UpdateExerciseDBRequest;
import lv.fitness_app.core.responses.UpdateExerciseDBResponse;
import lv.fitness_app.core.services.UpdateExerciseDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UpdateExerciseDBController {

    @Autowired
    UpdateExerciseDBService updateExerciseDBService;

    @GetMapping(value = "/updateExerciseDB")
    public String showUpdatePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateExerciseDBRequest());
        return "UpdateExerciseDB";
    }


    @PostMapping("/updateExerciseDB")
    public String processUpdateExerciseDbRequest(@ModelAttribute(value = "request") UpdateExerciseDBRequest request, ModelMap modelMap) throws IOException, CsvException {
        UpdateExerciseDBResponse response = updateExerciseDBService.execute(request);
        modelMap.addAttribute("result", response.getResult());
        return "updateExerciseDB";
    }

}
