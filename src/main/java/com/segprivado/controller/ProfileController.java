package com.segprivado.controller;

import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.segprivado.entity.UserRole;
import com.segprivado.service.impl.UserRoleService;


@Controller
@RequestMapping("/")
public class ProfileController {
	private static final Log LOG_PROFILE = LogFactory.getLog(ProfileController.class);
	private static final String FORM_VIEW = "profile";
	
	@Autowired
	private UserRoleService userService;
	
	
	@PreAuthorize("hasRole('ROLE_PACIENTE')")
	@GetMapping("/profile/{id}")
	public String formProfilePatient(@Valid @ModelAttribute("profile") UserRole user, Model modelo) {
		LOG_PROFILE.info("Formulario de perfil");
		//modelo.addAttribute("user",userService.findUser(user.getId()));
		return FORM_VIEW;
	}
	
	@PreAuthorize("hasRole('ROLE_PACIENTE')")
	@PostMapping("/profile/saveProfile")
	public String saveProfile(@Valid @ModelAttribute("profile") UserRole user, Model modelo, RedirectAttributes flashMessenger) {
		userService.updateProfile(user);// editar foto a la vez que editamos el paciente
		
		String update = "Perfil editado correctamente";
		LOG_PROFILE.info(update);
		flashMessenger.addFlashAttribute("edit",update);
		
		return "redirect:/menu";
	}
}
