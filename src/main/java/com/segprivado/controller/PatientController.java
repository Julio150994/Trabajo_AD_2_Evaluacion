package com.segprivado.controller;

import java.io.IOException;

import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.segprivado.model.PatientModel;
import com.segprivado.service.impl.PatientImpl;
import com.segprivado.uploads.StorageService;


@Controller
@RequestMapping("/patients")
public class PatientController {
	private static final Log LOG_PACIENTE = LogFactory.getLog(PatientController.class);
	private static final String SHOW = "listPatients", FORM_VIEW = "formPatient", SHOW_ID = "showPatient";
	
	@Autowired
	@Qualifier("patientImpl")
	private PatientImpl pacientes;
	
	@Autowired
	StorageService storageService;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listPatients")
	public ModelAndView listPatients() {
		LOG_PACIENTE.debug("Analizando errores...");
		
		ModelAndView mavPatient = new ModelAndView(SHOW);
		mavPatient.addObject("pacientes",pacientes.listAllPatients());//mostramos todos los pacientes
		LOG_PACIENTE.info("Lista de pacientes mostrada");
		return mavPatient;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/formPatient","/formPatient/{id}"})
	public String showFormPatient(@Valid @ModelAttribute("patient") PatientModel patientModel, Model modelo) {
		LOG_PACIENTE.debug("Debugueando la vista del formulario");
		
		if(patientModel.getId() == 0) {
			LOG_PACIENTE.info("Formulario añadir paciente");
			modelo.addAttribute("patient",new PatientModel());
		}
		else {
			LOG_PACIENTE.info("Formulario editar paciente");
			modelo.addAttribute("patient",pacientes.findPatient(patientModel.getId()));
		}
		return FORM_VIEW;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/savePatient")
	public String savePatient(@Valid @ModelAttribute("patient") PatientModel patientModel, RedirectAttributes flashMessenger, Model modelo,
			@RequestParam("file") MultipartFile file) throws IOException {
		LOG_PACIENTE.info("Analizando errores...");
		
		if(patientModel.getId() == 0) {// para datos introducidos correctamente
			pacientes.addPatient(patientModel);
			
			if(!file.isEmpty()) {
				String foto = storageService.store(file,patientModel.getId());
				LOG_PACIENTE.info(file);
				patientModel.setFoto(MvcUriComponentsBuilder.fromMethodName(ImagesController.class,"serveFile",foto).build().toUriString());// ruta para el perfil del paciente
				pacientes.updatePatient(patientModel);// editar paciente para añadir la foto
			}
			
			String addTxt = "Paciente añadido correctamente";
			LOG_PACIENTE.info(addTxt);
			flashMessenger.addFlashAttribute("add",addTxt);
		}
		else {
			if(!file.isEmpty()) {				
				if(patientModel.getFoto() != null)
					storageService.delete(patientModel.getFoto());
				
				String foto = storageService.store(file,patientModel.getId());
				patientModel.setFoto(MvcUriComponentsBuilder.fromMethodName(ImagesController.class,"serveFile",foto).build().toUriString());
			}
			else {
				PatientModel previousPatient = pacientes.findPatient(patientModel.getId());
				patientModel.setFoto(previousPatient.getFoto());// para cuando nuestro paciente sea nulo
			}
			
			pacientes.updatePatient(patientModel);// editar foto a la vez que editamos el paciente
			String updateTxt = "Paciente editado correctamente";
			LOG_PACIENTE.info(updateTxt);
			flashMessenger.addFlashAttribute("edit",updateTxt);
		}
		
		return "redirect:/patients/listPatients";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deletePatient/{id}")
	public String deletePatients(@PathVariable("id") int id, RedirectAttributes flashMessenger) {
		if(pacientes.removePatient(id) == 0) {
			LOG_PACIENTE.info("Eliminado correctamente");
			flashMessenger.addFlashAttribute("delete","Paciente eliminado correctamente");//después el mensaje
		}
		else {
			LOG_PACIENTE.error("Error al eliminar paciente");
			flashMessenger.addFlashAttribute("warning","Error al eliminar paciente");//después el mensaje
		}
		return "redirect:/patients/listPatients";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/showPatient/{id}")
	public String showPatient(@PathVariable int id, Model model) {
		LOG_PACIENTE.debug("Debugueando la vista mostrar por id");
		LOG_PACIENTE.info("Paciente mostrado por id");
		model.addAttribute("patient",pacientes.findPatient(id));
		return SHOW_ID;
	}
}
