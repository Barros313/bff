package com.avanade.bff.application.port.output;

import com.avanade.bff.domain.model.Customer;

public interface CustomerClient {

    Customer fetchCustomerById(Long id);
    Customer fetchCustomerByName(String name);
}
