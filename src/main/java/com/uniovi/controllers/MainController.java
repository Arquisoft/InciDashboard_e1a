package com.uniovi.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Incidence;
import com.uniovi.kafka.producer.KafkaProducer;
import com.uniovi.services.IncidenceService;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);
    
    @RequestMapping("/")
    public String landing(Model model) {
        return "redirect:incidences/list";
    }


}