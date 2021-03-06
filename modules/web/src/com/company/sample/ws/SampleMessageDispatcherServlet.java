package com.company.sample.ws;

import com.haulmont.cuba.core.sys.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class SampleMessageDispatcherServlet extends MessageDispatcherServlet {

    private boolean initialized = false;

    /**
     * Sets the Spring application context of a web app as a parent for the context of the DispatcherServlet context
     */
    @Override
    protected WebApplicationContext initWebApplicationContext() {
        WebApplicationContext wac = findWebApplicationContext();
        if (wac == null) {
            ApplicationContext parent = AppContext.getApplicationContext();
            wac = createWebApplicationContext(parent);
        }
        onRefresh(wac);
        // Publish the context as a servlet context attribute.
        String attrName = getServletContextAttributeName();
        getServletContext().setAttribute(attrName, wac);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Published WebApplicationContext of servlet '" + getServletName() +
                    "' as ServletContext attribute with name [" + attrName + "]");
        }
        return wac;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        if (!initialized) {
            super.init(config);
            initialized = true;
        }
    }
}
