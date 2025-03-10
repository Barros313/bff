package com.avanade.bff.infrastructure.adapter.input.controller;

import com.avanade.bff.application.port.input.GetCustomerUseCase;
import com.avanade.bff.domain.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerController(GetCustomerUseCase getCustomerUseCase) {
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @GetMapping("/bff/cliente/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return getCustomerUseCase.getCustomerById(id);
    }

    @GetMapping("/bff/cliente/name/{name}")
    public Customer getCustomerByName(@PathVariable("name") String name) {
        return getCustomerUseCase.getCustomerByName(name);
    }
}
