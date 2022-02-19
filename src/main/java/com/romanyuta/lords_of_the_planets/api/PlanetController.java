package com.romanyuta.lords_of_the_planets.api;

import com.romanyuta.lords_of_the_planets.model.Lord;
import com.romanyuta.lords_of_the_planets.model.Planet;

import com.romanyuta.lords_of_the_planets.model.PlanetForm;
import com.romanyuta.lords_of_the_planets.repository.LordRepository;
import com.romanyuta.lords_of_the_planets.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class PlanetController {


    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private LordRepository lordRepository;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/planetList" }, method = RequestMethod.GET)
    public String planetList(Model model) {

        Iterable<Planet> planets = planetRepository.findAll();

        model.addAttribute("planets", planets);

        return "planetList";
    }

    @RequestMapping(value = {"/planetInfo/{id}"},method = RequestMethod.GET)
    public String planetInfo(Model model, @PathVariable Long id){
        Planet planet = null;
        try {
            planet = planetRepository.getById(id);
        }catch (Exception  ex){
            model.addAttribute("errorMessage","Planet not found");
        }
        model.addAttribute("planet",planet);
        return "planetInfo";
    }

    @RequestMapping(value = {"planetInfo/{id}/delete"},method = RequestMethod.GET)
    public String showDeletePlanetsById(Model model, @PathVariable Long id){
        Planet planet = null;
        try{
            planet = planetRepository.getById(id);
        }catch (Exception  ex){
            model.addAttribute("errorMessage","Planet not found");
        }
        model.addAttribute("allowDelete",true);
        model.addAttribute("planet",planet);
        return "planetInfo";
    }

    @RequestMapping(value = {"/planetInfo/{id}/delete"},method = RequestMethod.POST)
    public String deletePlanetById(Model model, @PathVariable Long id){
        try{
            planetRepository.deleteById(id);
            return "redirect:/planetList";
        }catch (Exception ex){
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage",errorMessage);
            return "planetInfo";
        }
    }

    @RequestMapping(value = { "/addPlanet" }, method = RequestMethod.GET)
    public String showAddPlanetPage(Model model) {

        PlanetForm planetForm = new PlanetForm();
        model.addAttribute("planetForm", planetForm);

        return "addPlanet";
    }

    @RequestMapping(value = { "/addPlanet" }, method = RequestMethod.POST)
    public String savePlanet(Model model, //
                             @ModelAttribute("planetForm") PlanetForm planetForm) {

        String name = planetForm.getName();
        Long lord_id = planetForm.getLord_id();

        if (name!= null && name.length() > 0 && lord_id != null) {
            Optional<Lord> lord = lordRepository.findById(lord_id);
            if (lord.isPresent()) {
                Planet planet = new Planet(name, lord.get());
                planetRepository.save(planet);
            }
            return "redirect:/planetList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPlanet";
    }

}
