package com.foo.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.foo.logic.AvgCalculator;
import com.foo.webapp.controller.ControllerPackageMarker;
import com.foo.webapp.xml.StudentsMarshaler;

@Configuration
@ComponentScan(basePackageClasses = { ControllerPackageMarker.class })
@EnableWebMvc
public class UploadConfig extends WebMvcConfigurationSupport {

	@Autowired
	private WebApplicationContext context;

	public static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5 MB

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(MAX_FILE_SIZE);
		return resolver;
	}

	@Bean
	public AvgCalculator fileSaver() {
		return new AvgCalculator();
	}

	@Bean
	public StudentsMarshaler marshaler() {
		return new StudentsMarshaler();
	}

}