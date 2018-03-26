package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Notification;
import com.uniovi.entities.Operator;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;

@Controller
public class IncidenceController {
	@Autowired
	IncidenceService incidenceService;
	@Autowired
	OperatorService operatorService;
	
	@RequestMapping(value = "/incidences/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(value = "", required=false) String searchText) {
		
		Operator operator = operatorService.getOperator();//Operador especifico
		List<Incidence> incidences;
		List<Notification> notifications;

		if (searchText == null || searchText.isEmpty()) {
			incidences = operator.getIncidences(); //Obtengo las incidencias
		} else {
			incidences = incidenceService.searchIncidencesByNameAndDescription(searchText, operator);
		}
			notifications = new ArrayList<Notification>();
			operator.getIncidences().forEach(p-> notifications.add(new Notification(p)));   //Creo la lista de notificaciones para mostrar
		
			//Añado todo al modelo
			model.addAttribute("notifications", notifications);
			model.addAttribute("operator", operator);
			model.addAttribute("incidences", incidences);

		return "/incidences/list";
	}
	
	@RequestMapping("/incidences/details/{id}" )
	public String getDetail(Model model, @PathVariable Long id){
		model.addAttribute("incidence", incidenceService.verIncidencia(id));
		return "incidences/details";

	}
	
	@RequestMapping(value="/incidences/edit/{id}", method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Incidence incidence){
		Incidence original = incidenceService.verIncidencia(id);

		original.setStatus(incidence.getState()); //Otra opcion es hacerlo en los templates con javascript
		incidenceService.updateIncidence(original);
		return "redirect:/incidences/details/"+id;
	}
	
	@RequestMapping(value="/incidences/edit/{id}", method=RequestMethod.GET)
	public String setEditt(Model model, @PathVariable Long id){
		Incidence original = incidenceService.verIncidencia(id);
		model.addAttribute("incidence", original);
		return "incidences/edit";
	}
	
	
	@RequestMapping("/incidences/close/{id}" )
	public String close(@PathVariable Long id){
		Incidence incidence = incidenceService.verIncidencia(id);
		incidenceService.close(incidence);
		return "redirect:/incidences/list";
	}
}
