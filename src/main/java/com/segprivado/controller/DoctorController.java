package com.segprivado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.segprivado.model.DoctorModel;
import com.segprivado.service.impl.DoctorImpl;
import com.segprivado.service.impl.MedicineImpl;
import java.io.IOException;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/doctors")
public class DoctorController {
	private static final Log LOG_DOCTOR = LogFactory.getLog(DoctorController.class);
	private static final String SHOW = "listDoctors", FORM_VIEW = "formDoctor", SHOW_ID = "showDoctor",
			CONSULTATION = "consultation";
	
	@Autowired
	@Qualifier("doctorImpl")
	private DoctorImpl medicos;
	
	@Autowired
	@Qualifier("medicineImpl")
	private MedicineImpl medicamentos;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listDoctors")
	public ModelAndView listDoctors() {
		LOG_DOCTOR.info("Analizando errores...");
		
		ModelAndView mavPatient = new ModelAndView(SHOW);
		mavPatient.addObject("medicos",medicos.listAllDoctors());//mostramos todos los doctores de la tabla
		LOG_DOCTOR.info("Lista de médicos mostrada");
		return mavPatient;
	}
	
	/*---------Para la consulta por especialidades-----------*/
	@PreAuthorize("hasRole('ROLE_VISITANTE')")
	@GetMapping("/consultation")
	public ModelAndView showDoctorConsultation() {
		LOG_DOCTOR.info("Analizando errores...");
		
		ModelAndView mavPatient = new ModelAndView(CONSULTATION);
		mavPatient.addObject("medicos",medicos.listAllByEspecialidad());
		LOG_DOCTOR.info("Consulta mostrada para los visitantes");
		return mavPatient;
	}
	
	
	@PreAuthorize("hasRole('ROLE_PACIENTE')")
	@GetMapping("/listMedicines")
	public ModelAndView listMedicines() {
		LOG_DOCTOR.info("Analizando errores...");
		
		ModelAndView mavPatient = new ModelAndView(SHOW);
		mavPatient.addObject("medicamentos",medicamentos.listAllMedicines());//mostramos todos los pacientes
		LOG_DOCTOR.info("Lista de medicamentos mostrada");
		return mavPatient;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/formDoctor","/formDoctor/{id}"})
	public String formDoctor(@Valid @ModelAttribute("doctor") DoctorModel doctorModel, Model modelo) {
		LOG_DOCTOR.debug("Debugueando la vista del formulario");
		
		if(doctorModel.getId() == 0) {
			LOG_DOCTOR.info("Vista añadir doctor");
			modelo.addAttribute("doctor",new DoctorModel());
		}
		else {
			LOG_DOCTOR.info("Vista editar doctor");
			modelo.addAttribute("doctor",medicos.findDoctor(doctorModel.getId()));
		}
		return FORM_VIEW;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/saveDoctor")
	public String saveDoctor(@Valid @ModelAttribute("doctor") DoctorModel doctorModel, RedirectAttributes flashMessenger, Model modelo) throws IOException {
		LOG_DOCTOR.info("Analizando errores...");
		
		if(doctorModel.getId() == 0) {// para datos introducidos correctamente
			medicos.addDoctor(doctorModel);
			
			String addTxt = "Médico añadido correctamente";
			LOG_DOCTOR.info(addTxt);
			flashMessenger.addFlashAttribute("add",addTxt);
		}
		else {			
			medicos.updateDoctor(doctorModel);// editar foto a la vez que editamos el paciente
			String updateTxt = "Médico editado correctamente";
			LOG_DOCTOR.info(updateTxt);
			flashMessenger.addFlashAttribute("edit",updateTxt);
		}
		
		return "redirect:/doctors/listDoctors";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteDoctor/{id}")
	public String deleteDoctor(@PathVariable("id") int id, RedirectAttributes flashMessenger) {
		if(medicos.removeDoctor(id) == 0) {
			LOG_DOCTOR.info("Eliminado correctamente");
			flashMessenger.addFlashAttribute("delete","Doctor eliminado correctamente");//después el mensaje
		}
		else {
			LOG_DOCTOR.error("Error al eliminar paciente");
			flashMessenger.addFlashAttribute("warning","Error al eliminar doctor");//después el mensaje
		}
		
		return "redirect:/doctors/listDoctors";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/showDoctor/{id}")
	public String showDoctor(@PathVariable int id, Model model) {
		LOG_DOCTOR.debug("Debugueando la vista mostrar por id");
		LOG_DOCTOR.info("Doctor mostrado por id");
		model.addAttribute("doctor",medicos.findDoctor(id));
		return SHOW_ID;
	}
}
