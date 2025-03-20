package com.avanade.bff.infrastructure.adapter.input.controller;

import com.avanade.bff.application.service.CustomerService;
import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return customer data if id exists")
    void getCustomerById_Success() throws Exception {

        Customer customer = new Customer(
                1L,
                "John Doe",
                "Segment",
                null,
                null
        );

        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);

        this.mockMvc.perform(get("/bff/customer/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(customer)));

        verify(customerService, times(1)).getCustomerById(customer.getId());
    }

    @Test
    @DisplayName("Should throw an exception if id not found")
    void getCustomerById_NotFound() throws Exception {

        long nonExistingId = 1L;

        when(customerService.getCustomerById(nonExistingId))
                .thenThrow(new CustomerNotFoundException("Cliente não encontrado"));

        this.mockMvc.perform(get("/bff/customer/" + nonExistingId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cliente não encontrado"));

        verify(customerService, times(1)).getCustomerById(nonExistingId);
    }

    @Test
    @DisplayName("Should return customer data if name exists")
    void getCustomerByName_Success() throws Exception {

        Customer customer = new Customer(
                1L,
                "John Doe",
                "Segment",
                null,
                null
        );

        when(customerService.getCustomerByName(customer.getName())).thenReturn(customer);

        this.mockMvc.perform(get("/bff/customer/name/" + customer.getName()))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(customer)));

        verify(customerService, times(1)).getCustomerByName(customer.getName());
    }

    @Test
    @DisplayName("Should throw an exception when name not found")
    void getCustomerByName_NotFound() throws Exception {

        String nonExistingName = "John Doe";

        when(customerService.getCustomerByName(nonExistingName))
                .thenThrow(new CustomerNotFoundException("Cliente não encontrado"));

        this.mockMvc.perform(get("/bff/customer/name/" + nonExistingName))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cliente não encontrado"));

        verify(customerService, times(1)).getCustomerByName(nonExistingName);
    }
}
