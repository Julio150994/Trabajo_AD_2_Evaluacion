package com.segprivado.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.segprivado.entity.UserRole;
import com.segprivado.service.impl.UserRoleService;


@Controller
@RequestMapping("/")
public class LoginController {
	private static final Log LOGIN = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService users;
	
	
	/*----------Métodos para registrar un usuario----------------*/
	@GetMapping("/auth/register")
	public String formRegister(Model model, @RequestParam(name="error",required=false) String error,
			RedirectAttributes messageRegister) {
		model.addAttribute("user",new UserRole());
		
		if(error != null) {
			error = "Usuario y/o contraseña erróneos";
			LOGIN.error(error);
			messageRegister.addFlashAttribute("error",error);
		}
		return "register";//hacer un mejor diseño visual de esta vista
	}
	
	@PostMapping("/auth/registerUser")
	public String registerUser(@Valid @ModelAttribute("user") UserRole user, RedirectAttributes success) {
		users.registerUser(user);
		String registerSuccess = "¡¡Usuario "+user.getUsername()+" registrado correctamente!!";
		LOGIN.info(registerSuccess);
		success.addFlashAttribute("success",registerSuccess);
		return "redirect:/menu";//hacia el menú principal sin llegar a iniciar sesión
	}
	
	/*----------Métodos para iniciar sesión con ese usuario ya registrado----------------*/
	@GetMapping("/auth/login")
	public String login(@ModelAttribute("user") UserRole user, @RequestParam(name="error",required=false) String error, Model modelo,
			@RequestParam(name="logout",required=false) String logout, RedirectAttributes flashMessage) {
		
		modelo.addAttribute("user",new UserRole());// para darnos acceso al login
		
		if(error != null) {
			error = "Usuario y/o contraseña erróneos";
			modelo.addAttribute("error",error);
		}
		if(logout != null) {
			logout = "Has cerrado sesión";
			modelo.addAttribute("logout",logout);
		}
		
		return "login";//hacer un mejor diseño visual de esta vista
	}
	
	
	@PreAuthorize(value="isAuthenticated()")
	@GetMapping("/auth/loginMenu")
	public String loginMenu(@Valid @ModelAttribute("user") UserRole user, RedirectAttributes flashMessage, Model modelo) {
		String loginSuccess = "¡¡Usuario "+user.getUsername()+" registrado correctamente!!";
		LOGIN.info(loginSuccess);
		modelo.addAttribute("login",loginSuccess);
		return "redirect:/menu";//visualizamos el menú con un usuario logueado
	}
}
