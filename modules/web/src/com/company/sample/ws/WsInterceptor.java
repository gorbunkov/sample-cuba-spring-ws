package com.company.sample.ws;

import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.TrustedClientService;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.inject.Inject;

/**
 * Intercepts invocations of SOAP WS endpoints methods. It fills the AppContext with a system authentication.
 */
public class WsInterceptor {

    @Inject
    protected TrustedClientService trustedClientService;

    @Inject
    protected Configuration configuration;

    private Object aroundInvoke(ProceedingJoinPoint ctx) throws Throwable {
        String trustedClientPassword = configuration.getConfig(WebAuthConfig.class).getTrustedClientPassword();
        UserSession systemSession = trustedClientService.getSystemSession(trustedClientPassword);
        SecurityContext securityContext = new SecurityContext(systemSession);
        SecurityContext previousSecurityContext = AppContext.getSecurityContext();
        AppContext.setSecurityContext(securityContext);
        try {
            return ctx.proceed();
        } finally {
            AppContext.setSecurityContext(previousSecurityContext);
        }
    }
}
