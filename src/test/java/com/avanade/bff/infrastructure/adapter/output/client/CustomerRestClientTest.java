package com.avanade.bff.infrastructure.adapter.output.client;

import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.model.Customer;
import com.avanade.bff.infrastructure.config.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
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

    @Value(value = "${api.spring.demo.customer.url}")
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
        final int customerId = 7;

        server.expect(ExpectedCount.once(), requestTo(backendUrl + customerId))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CustomerNotFoundException.class, () -> {
            restTemplate.getForObject(backendUrl + customerId, Customer.class);
        });

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by id when 200 status code then return customer")
    void fetchCustomerById_whenSuccess_thenReturnCustomer() throws JsonProcessingException {
        String responseBody = mapper.writeValueAsString(sampleCustomer);

        server.expect(ExpectedCount.once(), requestTo(backendUrl + sampleCustomer.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        Customer result = restTemplate.getForEntity(backendUrl + sampleCustomer.getId(), Customer.class).getBody();

        assertThat(result).isEqualTo(sampleCustomer);

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by name when 404 status code then throw not found")
    void fetchCustomerByName_when404Error_thenThrowNotFound() {
        final String customerName = "Maria";

        server.expect(ExpectedCount.once(), requestTo(backendUrl + "name/" + customerName))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CustomerNotFoundException.class, () -> {
            restTemplate.getForObject(backendUrl + "name/" + customerName, Customer.class);
        });

        server.verify();
    }

    @Test
    @DisplayName("Fetch customer by name when 200 status code then return customer")
    void fetchCustomerByName_whenSuccess_thenReturnCustomer() throws JsonProcessingException {
        String responseBody = mapper.writeValueAsString(sampleCustomer);

        // MockRestRequestMatchers.requestTo() does not encode the URL properly
        String customerNameUrlEncoded = sampleCustomer.getName().replace(" ", "%20");

        server.expect(ExpectedCount.once(), requestTo(backendUrl + "name/" + customerNameUrlEncoded))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        Customer result = restTemplate.getForEntity(backendUrl + "name/" + sampleCustomer.getName(), Customer.class).getBody();

        assertThat(result).isEqualTo(sampleCustomer);

        server.verify();
    }

}