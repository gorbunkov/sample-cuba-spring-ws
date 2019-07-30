package com.company.sample.ws;

import com.haulmont.cuba.core.sys.AbstractWebAppContextLoader;

import javax.servlet.*;

/**
 * A ServletContextListener that creates the SampleMessageDispatcherServlet in case of single WAR deployment.
 */
public class SampleMessageDispatcherServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        registerServlet(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    protected void registerServlet(ServletContext servletContext) {
        SampleMessageDispatcherServlet servlet = new SampleMessageDispatcherServlet();
        servlet.setContextConfigLocation("classpath:com/company/sample/ws-spring.xml");
        servlet.setTransformWsdlLocations(true);
        //init() must be invoked here. Otherwise the spring context initialization (SampleMessageDispatcherServlet) fails, because parent
        //context beans (e.g. dataManager) can't be found.
        try {
            servlet.init(new AbstractWebAppContextLoader.CubaServletConfig("ws", servletContext));
        } catch (ServletException e) {
            throw new RuntimeException("An error occurred while initializing SampleMessageDispatcherServlet", e);
        }
        ServletRegistration.Dynamic servletReg = servletContext.addServlet("ws", servlet);
        servletReg.addMapping("/ws/*");
    }
}