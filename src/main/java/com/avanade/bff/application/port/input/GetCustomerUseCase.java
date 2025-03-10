package com.avanade.bff.application.port.input;

import com.avanade.bff.domain.model.Customer;

public interface GetCustomerUseCase {

    Customer getCustomerById(Long id);
    Customer getCustomerByName(String name);
}
