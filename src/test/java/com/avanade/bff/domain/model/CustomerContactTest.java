package com.avanade.bff.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerContactTest {

    @Test
    @DisplayName("If both contacts have the same values then return equals")
    void testProperties() {
        CustomerContact contact1 = new CustomerContact("email", "phone");
        CustomerContact contact2 = new CustomerContact("email", "phone");

        assertThat(contact1.equals(contact2));
    }

    @Test
    @DisplayName("Assert that email is returned")
    void email() {
        CustomerContact contact = new CustomerContact("email", "phone");

        assertThat(contact.email()).isEqualTo("email");
    }

    @Test
    @DisplayName("Assert that phone is returned")
    void phone() {
        CustomerContact contact = new CustomerContact("email", "phone");

        assertThat(contact.phone()).isEqualTo("phone");
    }
}