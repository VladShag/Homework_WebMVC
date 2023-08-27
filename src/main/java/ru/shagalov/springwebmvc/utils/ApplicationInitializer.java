package ru.shagalov.springwebmvc.utils;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        {
            final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.scan("ru.shagalov.springwebmvc");
            context.refresh();
            final DispatcherServlet servlet = new DispatcherServlet(context);
            final ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
            registration.setLoadOnStartup(1);
            registration.addMapping("/");
        }
    }
}
