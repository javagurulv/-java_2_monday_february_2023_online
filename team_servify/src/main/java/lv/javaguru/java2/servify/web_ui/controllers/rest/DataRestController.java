package lv.javaguru.java2.servify.web_ui.controllers.rest;

import lv.javaguru.java2.servify.core.database.ColorRepository;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaColorRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.Color;
import lv.javaguru.java2.servify.core.domain.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class DataRestController {

    @Autowired
    private JpaDetailRepository detailRepository;
    @Autowired
    private JpaColorRepository colorRepository;

    @GetMapping("/details")
    public List<Detail> getDetails() {
        return detailRepository.findAll();
    }

    @GetMapping("/colors")
    public List<Color> getColors() {
        return colorRepository.findAll();
    }
}