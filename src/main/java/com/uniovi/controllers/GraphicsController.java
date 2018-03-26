package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Status;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;

@Controller
public class GraphicsController {
	@Autowired
	private IncidenceService incidenceService;
	
	@Autowired
	private OperatorService operaService;
	
	@RequestMapping("/stadistics")
	public String getStatistics(Model model) {
		model.addAttribute("numberOfTotalIncidences", incidenceService.findAll().size());
		model.addAttribute("numberOfOpenedIncidences", incidenceService.countIncidencesType(Status.OPENED));
		model.addAttribute("numberOfCancelledIncidences", incidenceService.countIncidencesType(Status.CANCELLED));
		model.addAttribute("numberOfClosedIncidences", incidenceService.countIncidencesType(Status.CLOSED));
		model.addAttribute("numberOfAssignedIncidences", incidenceService.countIncidencesType(Status.ASSIGNED));
		model.addAttribute("operatorWithMoreIncidences", operaService.findOperatorWithMoreIncidences());
		
		return "estadisticas";
	}

	
}
