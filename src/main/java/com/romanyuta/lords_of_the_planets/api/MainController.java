package com.romanyuta.lords_of_the_planets.api;

import com.romanyuta.lords_of_the_planets.model.Lord;
import com.romanyuta.lords_of_the_planets.repository.LordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private LordRepository lordRepository;

    @Value("${error.message}")
    private String errorMessage;




    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {

        return "index";
    }


    @RequestMapping(value = { "/lazyLord" }, method = RequestMethod.GET)
    public String lazyLordList(Model model) {

        Iterable<Lord> lords =lordRepository.getLouderLords();
        model.addAttribute("lords", lords);

        return "lazyLord";
    }
    @RequestMapping(value = { "/youngLord" }, method = RequestMethod.GET)
    public String youngLordList(Model model) {

        Iterable<Lord> lords =lordRepository.getTopYoungLords();
        model.addAttribute("lords", lords);

        return "youngLord";
    }
}
