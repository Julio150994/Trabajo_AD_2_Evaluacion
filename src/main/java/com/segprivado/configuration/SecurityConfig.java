package com.segprivado.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.segprivado.service.impl.UserRoleService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userRoleService).passwordEncoder(cryptPassword());
	}
	
	@Bean
	public BCryptPasswordEncoder cryptPassword() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity secClinicapp) throws Exception {
		secClinicapp.csrf().disable().authorizeRequests()
		.antMatchers("/images/**","/auth/**","/webjars/**","/css/**","/js/**","/appointments/**","/doctors/**",
				"/medicines/**","/patients/**","/purchases/**","/purchase_medicines/**").permitAll()
		.antMatchers("/","/menu","/information","/contacts","/register","/registerUser","/login").permitAll()
		.antMatchers("/auth/loginMenu").authenticated() //autorizamos el acceso a las páginas deseadas con los roles de la base de datos
		/*-------Para las solicitudes de API Rest-----------*/
		.antMatchers(HttpMethod.POST,"/**").permitAll()
		.antMatchers(HttpMethod.PUT,"/**").permitAll()
		.antMatchers(HttpMethod.DELETE,"/**").permitAll()
		.antMatchers("/pagination").authenticated()
		.antMatchers("/doctors/showDoctorConsultation").access("hasRole('ROLE_VISITANTE')")
		.antMatchers("/doctors/listDoctors","/doctors/formDoctor", "/doctors/formDoctor/{id}",
				"/doctors/showDoctor","/doctors/deleteDoctor/{id}").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/patients/listPatients","/patients/formPatient", "/patients/formPatient/{id}",
				"/patients/showPatient","/patients/deletePatient/{id}").access("hasRole('ROLE_ADMIN')")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/auth/login")
		.defaultSuccessUrl("/menu",true)
		.loginProcessingUrl("/auth/loginMenu")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/auth/login?logout")
		.permitAll();
	}
}
