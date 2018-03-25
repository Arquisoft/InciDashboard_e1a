package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String list(Model model) {

		
		Operator operator = operatorService.getOperator();//Operador especifico
		List<Incidence> incidences = operator.getIncidences(); //Obtengo las incidencias
		List<Notification> notifications = new ArrayList<Notification>();
		operator.getIncidences().forEach(p-> notifications.add(new Notification(p)));   //Creo la lista de notificaciones para mostrar
		
		//Añado todo al modelo
		model.addAttribute("notifications", notifications);
		model.addAttribute("operator", operator);
		model.addAttribute("incidences", incidences);

		return "/incidences/list";
	}
	
	@RequestMapping(value = "/incidences/{id}", method = RequestMethod.GET)
	public String info(Model model, @PathVariable Long id) {
		
		Incidence incidence = incidenceService.verIncidencia(id); //Obtener incidencia especifica
		Operator operator = operatorService.getOperator(); //Operador actual
		List<Notification> notifications = new ArrayList<Notification>(); 
		operator.getIncidences().forEach(p-> notifications.add(new Notification(p)));   //Creo la lista de notificaciones para mostrar

		//Añado todo al modelo
		model.addAttribute("notifications", notifications);
		model.addAttribute("operator", operator);
		model.addAttribute("incidence", incidence);
		
		return "/incidences/info";
	}
}
