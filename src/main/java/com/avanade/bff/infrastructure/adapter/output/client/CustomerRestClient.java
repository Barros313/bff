package com.avanade.bff.infrastructure.adapter.output.client;

import com.avanade.bff.application.port.output.CustomerClient;
import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerRestClient implements CustomerClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.springdemo.url}")
    private String backendUrl;

    @Override
    public Customer fetchCustomerById(Long id) {
        Customer customer = restTemplate.getForObject(backendUrl + "cliente/" + id, Customer.class);
        if (customer == null) {
            throw new CustomerNotFoundException("Cliente não encontrado");
        }

        return customer;
    }

    @Override
    public Customer fetchCustomerByName(String name) {
        Customer customer = restTemplate.getForObject(backendUrl + "cliente/name/" + name, Customer.class);
        if (customer == null) {
            throw new CustomerNotFoundException("Cliente não encontrado");
        }

        return customer;
    }
}
