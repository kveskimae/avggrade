package com.foo.webapp;

import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class UploadInitializer implements WebApplicationInitializer {
	
	static Logger log = LoggerFactory.getLogger(UploadInitializer.class);
    
    public void onStartup(ServletContext servletContext) throws ServletException {
	    log.info("Initializing web app started");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(UploadConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/rest/*");
		servlet.setLoadOnStartup(1);
	    log.info("Initializing web app complete");
    }
    

}