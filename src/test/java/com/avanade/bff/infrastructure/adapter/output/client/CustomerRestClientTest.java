package com.avanade.bff.infrastructure.adapter.output.client;

import com.avanade.bff.application.port.output.CustomerClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerRestClientTest {

    @Autowired
    private CustomerClient

    @Autowired
    TestRestTemplate testRestTemplate;

    @Value("${api.springdemo.url}")
    private String backendUrl;

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchCustomerById_Success() {
    }

    @Test
    void fetchCustomerById_Fail() {

    }

    @Test
    void fetchCustomerByName() {
    }
}