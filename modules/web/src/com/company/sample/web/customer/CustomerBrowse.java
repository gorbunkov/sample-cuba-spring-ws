package com.company.sample.web.customer;

import com.company.sample.entity.Customer;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}