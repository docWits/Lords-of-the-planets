package com.romanyuta.lords_of_the_planets.api;

import com.romanyuta.lords_of_the_planets.model.Lord;
import com.romanyuta.lords_of_the_planets.model.LordForm;
import com.romanyuta.lords_of_the_planets.repository.LordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LordController {


    @Autowired
    private LordRepository lordRepository;
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/lordList" }, method = RequestMethod.GET)
    public String lordList(Model model) {

        Iterable<Lord> lords =lordRepository.findAll();
        model.addAttribute("lords", lords);

        return "lordList";
    }

    @RequestMapping(value = { "/addLord" }, method = RequestMethod.GET)
    public String showAddLordPage(Model model) {

        LordForm lordForm = new LordForm();
        model.addAttribute("lordForm", lordForm);

        return "addLord";
    }

    @RequestMapping(value = { "/addLord" }, method = RequestMethod.POST)
    public String saveLord(Model model, @ModelAttribute("lordForm") LordForm lordForm) {

        String name = lordForm.getName();
        int age = lordForm.getAge();


        try {
            if (name!= null && name.length() > 0 && age != 0) {
                Lord newLord = new Lord(name, age);
                lordRepository.save(newLord);
            }
            return "redirect:/lordList";
        }catch (Exception ex){
            model.addAttribute("errorMessage", errorMessage);
            return "addLord";
        }
    }
}

