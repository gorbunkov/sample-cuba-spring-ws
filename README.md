# Spring WS SOAP Endpoint Sample

 The project demonstrates how to configure a SOAP endpoint in Cuba application using the [Spring Web Services](https://spring.io/projects/spring-ws).
 
 The endpoint controller is [CustomerEndpoint.java](modules/web/src/com/company/sample/ws/endpoint/CustomerEndpoint.java). The `handleCreateCustomerRequest` method there is annotated with the [@WsAuthenticated](modules/web/src/com/company/sample/ws/WsAuthenticated.java). It is required to perform a system authentication. This annotation is processed in the [WsInterceptor.java](modules/web/src/com/company/sample/ws/WsInterceptor.java)
 
 In the custom message dispatcher servlet [SampleMessageDispatcherServlet.java](modules/web/src/com/company/sample/ws/SampleMessageDispatcherServlet.java) the spring context of the web app is set as a parent of the web-service spring context. After that we may use Cuba Spring beans (e.g. `DataManager`) in web services endpoints.  
 
 The MessageDispatcherServlet is configured in the [web.xml](modules/web/web/WEB-INF/web.xml). 
 
 The Spring config file is [ws-spring.xml](modules/web/src/com/company/sample/ws-spring.xml).
 
 The wsdl file is [customer.wsdl](com/company/sample/ws/wsdl/customer.wsdl)
 
 After you start the application you may get a WSDL by URL `http://localhost:8080/app/ws/customer.wsdl`.
 
 The `http://localhost:8080/app/ws/customerService/` is the URL where the web service can be invoked.