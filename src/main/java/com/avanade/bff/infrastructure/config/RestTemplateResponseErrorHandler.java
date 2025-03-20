package com.avanade.bff.infrastructure.config;

import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.exception.ServiceUnavailableException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() ||
                response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
        } else if (response.getStatusCode().is4xxClientError()) {
            throw new CustomerNotFoundException("Cliente não encontrado");
        } else if (response.getStatusCode().isSameCodeAs(HttpStatus.valueOf(0))) {
            throw new ServiceUnavailableException("Serviço indisponível");
        }
    }
}
