package com.avanade.bff.application.service;

import com.avanade.bff.application.port.input.GetCustomerUseCase;
import com.avanade.bff.application.port.output.CustomerClient;
import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements GetCustomerUseCase {

    private final CustomerClient customerClient;

    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerClient.fetchCustomerById(id);

        if (customer == null) throw new CustomerNotFoundException("Customer not found");

        return customer;
    }

    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = customerClient.fetchCustomerByName(name);

        if (customer == null) throw new CustomerNotFoundException("Customer not found");

        return customer;
    }
}
