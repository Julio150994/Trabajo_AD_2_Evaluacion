package com.segprivado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/")
public class IndexController {
	private static final Log LOG = LogFactory.getLog(IndexController.class);
	private static final String SHOW_PAGINATION = "pagination";
	
	@GetMapping("/")
	public String redirectMenu() {
		return "redirect:/menu";
	}	
	
	@GetMapping("/menu")
	public String showMenu() {
		return "clinic_Index";//menú principal
	}
	
	/*-----------Información de la empresa y contactos-----------------*/
	@GetMapping("/information")
	public String showInformationEmployee() {
		return "information";
	}
	
	@GetMapping("/contacts")
	public String showContacts() {
		return "contacts";
	}
	
	/*------Mostramos paginaciones------*/
	@GetMapping("/pagination")
	public String paginate() {
		LOG.debug("Analizando errores...");
		LOG.info("Paginación mostrada correctamente");
		return SHOW_PAGINATION;
	}
}
