package com.segprivado.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("dozerApp")
public class DozerApp {
	
	@Bean
	public DozerBeanMapper dozer() {
		return new DozerBeanMapper();
	}
}
