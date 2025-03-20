package com.avanade.bff.application.service;

import com.avanade.bff.application.port.output.CustomerClient;
import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerClient customerClient;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should return customer if id found in database")
    void getExistingCustomerById() {
        final long customerId = 1L;

        Customer customer = new Customer(
                customerId,
                "Maria da Silva",
                "Classic",
                null,
                null);

        when(customerClient.fetchCustomerById(customerId)).thenReturn(customer);

        assertThat(customerService.getCustomerById(customerId)).isEqualTo(customer);

        verify(customerClient, times(1)).fetchCustomerById(customerId);
    }

    @Test
    @DisplayName("Should throw an exception if id doesn't exist")
    void getNonExistingCustomerById() {
        final long nonExistingCustomerId = 11;

        when(customerClient.fetchCustomerById(nonExistingCustomerId))
                .thenThrow(new CustomerNotFoundException("Cliente não encontrado"));

        Exception thrown = Assertions.assertThrows(CustomerNotFoundException.class, () -> {
           customerService.getCustomerById(nonExistingCustomerId);
        });

        assertThat(thrown.getMessage()).isEqualTo("Cliente não encontrado");

        verify(customerClient, times(1)).fetchCustomerById(nonExistingCustomerId);
    }

    @Test
    @DisplayName("Should return customer if name found in database")
    void getExistingCustomerByName() {
        final String existingCustomerName = "Maria da Silva";

        Customer customer = new Customer(
                1L,
                existingCustomerName,
                "Classic",
                null,
                null);

        when(customerClient.fetchCustomerByName(existingCustomerName)).thenReturn(customer);

        assertThat(customerService.getCustomerByName(existingCustomerName)).isEqualTo(customer);

        verify(customerClient, times(1)).fetchCustomerByName(existingCustomerName);

    }

    @Test
    @DisplayName("Should throw exception if name doesn't exist")
    void getNonExistingCustomerByName() {
        final String nonExistingCustomerName = "Gabriel Barros";

        when(customerClient.fetchCustomerByName(nonExistingCustomerName))
                .thenThrow(new CustomerNotFoundException("Cliente não encontrado"));

        Exception thrown = Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getCustomerByName(nonExistingCustomerName);
        });

        assertThat(thrown.getMessage()).isEqualTo("Cliente não encontrado");

        verify(customerClient, times(1)).fetchCustomerByName(nonExistingCustomerName);
    }
}