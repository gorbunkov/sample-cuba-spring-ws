package com.company.sample.web.customer;

import com.company.sample.entity.Customer;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}