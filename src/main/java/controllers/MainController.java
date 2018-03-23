package controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import entities.Incidence;
import kafka.producer.KafkaProducer;
import services.IncidenceService;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    
    @Autowired
    IncidenceService incidenceService;
    
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/")
    public String landing(Model model) {
    	model.addAttribute("incidencesList", incidenceService.findAll());
        return "index";
    }

    /**
     * Comprobar conexión con kafka automatizar luego
     * @param model
     * @param incidence
     * @return
     */
    @RequestMapping(value="/send", method = RequestMethod.POST)
    public String send(Model model, @ModelAttribute Incidence incidence) {
        kafkaProducer.send("exampleTopic", incidence.toString());
        return "incidences/add";
    }

    /**
     * Pa probar
     * @param model
     * @param incidence
     * @return
     */
    @RequestMapping("/send")
    public String send() {
        return "incidences/add";
    }

}