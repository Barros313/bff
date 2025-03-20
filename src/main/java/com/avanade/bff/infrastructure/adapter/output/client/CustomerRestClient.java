package com.avanade.bff.infrastructure.adapter.output.client;

import com.avanade.bff.application.port.output.CustomerClient;
import com.avanade.bff.domain.exception.ServiceUnavailableException;
import com.avanade.bff.domain.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerRestClient implements CustomerClient {

    private final RestTemplate restTemplate;

    public CustomerRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value(value = "${api.springdemo.url}")
    private String backendUrl;

    @Override
    public Customer fetchCustomerById(Long id) {
        try {
            return restTemplate.getForObject(backendUrl + "cliente/" + id, Customer.class);
        } catch (ResourceAccessException e) {
            throw new ServiceUnavailableException("Serviço indisponível");
        }
    }

    @Override
    public Customer fetchCustomerByName(String name) {
        try {
            return restTemplate.getForObject(backendUrl + "cliente/name/" + name, Customer.class);
        } catch (ResourceAccessException e) {
            throw new ServiceUnavailableException("Serviço indisponível");
        }
    }
}
