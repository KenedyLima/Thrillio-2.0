package com.thrillio.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		ServletRegistration.Dynamic customDispatcherServlet = servletContext.addServlet("customDispatcherServlet", dispatcherServlet);
		customDispatcherServlet.setLoadOnStartup(1);
		customDispatcherServlet.addMapping("/*");
	}

}
