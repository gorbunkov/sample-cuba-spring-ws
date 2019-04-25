package com.company.sample.ws.endpoint;

import com.company.sample.entity.Customer;
import com.company.sample.ws.WsAuthenticated;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.inject.Inject;

@Endpoint
public class CustomerEndpoint {

    private Logger log = LoggerFactory.getLogger(CustomerEndpoint.class);

    @Inject
    protected DataManager dataManager;

    @Inject
    protected Metadata metadata;

    private static final String NAMESPACE = "http://company.com/ws/schemas";

    @WsAuthenticated
    @PayloadRoot(namespace = NAMESPACE, localPart = "createCustomerRequest")
    @ResponsePayload
    public Element handleCreateCustomerRequest(@RequestPayload Element createCustomerRequest) {
        Element firstNameElement = createCustomerRequest.element("firstName");
        Element lastNameElement = createCustomerRequest.element("lastName");
        String firstName = firstNameElement != null ? firstNameElement.getText() : null;
        String lastName = lastNameElement != null ? lastNameElement.getText() : null;
        Customer customer = metadata.create(Customer.class);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Customer commitedCustomer = dataManager.commit(customer);
        log.info("Customer {} saved", metadata.getTools().getInstanceName(customer));
        Document document = DocumentHelper.createDocument();
        Element createCustomerResponse = document.addElement("createCustomerResponse", NAMESPACE)
                .addElement("id", NAMESPACE)
                .addText(commitedCustomer.getId().toString());
        return createCustomerResponse;
    }
}
