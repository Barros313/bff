package com.avanade.bff.infrastructure.adapter.input.controller;

import com.avanade.bff.application.port.input.GetCustomerUseCase;
import com.avanade.bff.domain.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bff/customer/")
public class CustomerController {

    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerController(GetCustomerUseCase getCustomerUseCase) {
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getCustomerUseCase.getCustomerById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(getCustomerUseCase.getCustomerByName(name));
    }
}
