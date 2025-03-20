package com.avanade.bff.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDocumentTest {

    @Test
    @DisplayName("If both documents have the same values then return equals")
    void testProperties() {
        CustomerDocument document1 = new CustomerDocument("documentNumber", "documentType");
        CustomerDocument document2 = new CustomerDocument("documentNumber", "documentType");

        assertEquals(document1, document2);
    }

    @Test
    @DisplayName("Assert that document number is returned")
    void documentNumber() {
        CustomerDocument document = new CustomerDocument("documentNumber", "documentType");

        assertEquals("documentNumber", document.documentNumber());
    }

    @Test
    @DisplayName("Assert that document type is returned")
    void documentType() {
        CustomerDocument document = new CustomerDocument("documentNumber", "documentType");

        assertEquals("documentType", document.documentType());
    }
}