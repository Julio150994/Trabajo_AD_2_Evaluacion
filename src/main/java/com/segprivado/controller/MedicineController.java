package com.segprivado.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.segprivado.model.MedicineModel;
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
@RequestMapping("/medicines")
public class MedicineController {
	private static final Log LOG_MEDICINE = LogFactory.getLog(MedicineController.class);
	private static final String SHOW = "listMedicines", FORM_VIEW = "formMedicine", SHOW_ID = "showMedicine";
	
	@Autowired
	@Qualifier("medicineImpl")
	private MedicineImpl medicamentos;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listMedicines")
	public ModelAndView listMedicines() {
		LOG_MEDICINE.info("Analizando errores...");
		
		ModelAndView mavPatient = new ModelAndView(SHOW);
		mavPatient.addObject("medicamentos",medicamentos.listAllMedicines());//mostramos todos los pacientes
		LOG_MEDICINE.info("Lista de medicamentos mostrada");
		return mavPatient;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/formMedicine","/formMedicine/{id}"})
	public String showFormMedicine(@Valid @ModelAttribute("medicine") MedicineModel medicineModel, Model modelo) {
		LOG_MEDICINE.debug("Debugueando la vista del formulario");
		
		if(medicineModel.getId() == 0) {
			LOG_MEDICINE.info("Vista añadir medicamento");
			modelo.addAttribute("medicine",new MedicineModel());
		}
		else {
			LOG_MEDICINE.info("Vista editar medicamento");
			modelo.addAttribute("medicine",medicamentos.findMedicine(medicineModel.getId()));
		}
		return FORM_VIEW;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/saveMedicine")
	public String saveMedicine(@Valid @ModelAttribute("medicine") MedicineModel medicineModel, RedirectAttributes flashMessenger, Model modelo) throws IOException {
		LOG_MEDICINE.info("Analizando errores...");
		
		if(medicineModel.getId() == 0) {// para datos introducidos correctamente
			medicamentos.addMedicine(medicineModel);
			
			String addTxt = "Medicamento añadido correctamente";
			LOG_MEDICINE.info(addTxt);
			flashMessenger.addFlashAttribute("add",addTxt);
		}
		else {			
			medicamentos.updateMedicine(medicineModel);// editar foto a la vez que editamos el paciente
			String updateTxt = "Medicamento editado correctamente";
			LOG_MEDICINE.info(updateTxt);
			flashMessenger.addFlashAttribute("edit",updateTxt);
		}
		
		return "redirect:/medicines/listMedicines";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/deleteMedicine/{id}")
	public String deleteMedicine(@PathVariable("id") int id, RedirectAttributes flashMessenger) {
		if(medicamentos.removeMedicine(id) == 0) {
			LOG_MEDICINE.info("Eliminado correctamente");
			flashMessenger.addFlashAttribute("delete","Paciente eliminado correctamente");//después el mensaje
		}
		else {
			LOG_MEDICINE.error("Error al eliminar paciente");
			flashMessenger.addFlashAttribute("warning","Error al eliminar paciente");//después el mensaje
		}
		return "redirect:/medicines/listMedicines";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/showMedicine/{id}")
	public String showMedicine(@PathVariable int id, Model model) {
		LOG_MEDICINE.debug("Debugueando la vista mostrar por id");
		LOG_MEDICINE.info("Medicamento mostrado por id");
		model.addAttribute("medicine",medicamentos.findMedicine(id));
		return SHOW_ID;
	}
}
