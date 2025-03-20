package com.avanade.bff.infrastructure.adapter.output.client;

import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.exception.ServiceUnavailableException;
import com.avanade.bff.domain.model.Customer;
import com.avanade.bff.infrastructure.config.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@ExtendWith(SpringExtension.class)
@RestClientTest
class CustomerRestClientTest {

    private final RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler())
            .build();

    private final MockRestServiceServer server =
            MockRestServiceServer.bindTo(restTemplate)
                    .ignoreExpectOrder(true)
                    .build();

    private final Customer sampleCustomer =
            new Customer(6L, "Lucas Silva", "Prime", null, null);

    private final ObjectMapper mapper = new ObjectMapper();

    @Value(value = "${api.springdemo.url}")
    private String backendUrl;

    @Test
    @DisplayName("Test dependencies should not be null")
    void dependenciesNotNull() {
        assertNotNull(this.server);
        assertNotNull(this.restTemplate);
    }

    @Test
    @DisplayName("Fetch customer by id when 404 status code then throw not found")
    void fetchCustomerById_when404Error_thenThrowNotFound() {
        server.expect(ExpectedCount.once(), requestTo("/cliente/7"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CustomerNotFoundException.class, () -> {
            restTemplate.getForObject("/cliente/7", Customer.class);
        });

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by id when 200 status code then return customer")
    void fetchCustomerById_whenSuccess_thenReturnCustomer() throws JsonProcessingException {
        String responseBody = mapper.writeValueAsString(sampleCustomer);

        server.expect(ExpectedCount.once(), requestTo("/cliente/6"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        Customer result = restTemplate.getForEntity("/cliente/6", Customer.class).getBody();

        assertThat(result).isEqualTo(sampleCustomer);

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by name when 404 status code then throw not found")
    void fetchCustomerByName_when404Error_thenThrowNotFound() {
        server.expect(ExpectedCount.once(), requestTo("/cliente/name/Maria"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CustomerNotFoundException.class, () -> {
            restTemplate.getForObject("/cliente/name/Maria", Customer.class);
        });

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by name when 200 status code then return customer")
    void fetchCustomerByName_whenSuccess_thenReturnCustomer() throws JsonProcessingException {
        String responseBody = mapper.writeValueAsString(sampleCustomer);

        server.expect(ExpectedCount.once(), requestTo("/cliente/name/Lucas%20Silva"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        Customer result = restTemplate.getForEntity("/cliente/name/Lucas Silva", Customer.class).getBody();

        assertThat(result).isEqualTo(sampleCustomer);

        server.verify();
    }

}