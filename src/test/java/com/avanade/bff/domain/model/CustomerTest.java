package com.avanade.bff.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("If both customers have the same values then return equals")
    void equals_whenBothCustomersHaveTheSameValues_thenReturnEquals() {
        Customer customer1 = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        Customer customer2 = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        assertTrue(customer1.equals(customer2));
    }

    @Test
    @DisplayName("Get and set id")
    void setId_whenSetId_thenGetIdShouldReturnValue() {
        Customer customer = new Customer();

        customer.setId(1L);

        assertEquals(1L, customer.getId());
    }

    @Test
    @DisplayName("Get and set name")
    void setName() {
        Customer customer = new Customer();

        customer.setName("John Doe");

        assertEquals("John Doe", customer.getName());
    }

    @Test
    @DisplayName("Get and set segment name")
    void setSegmentName() {
        Customer customer = new Customer();

        customer.setSegmentName("Segment 1");


        assertEquals("Segment 1", customer.getSegmentName());
    }

    @Test
    @DisplayName("Get and set documents")
    void setDocuments() {
        CustomerDocument customerDocument = new CustomerDocument("123456789", "RG");
        Customer customer = new Customer();

        List<CustomerDocument> documents = new ArrayList<>();
        documents.add(customerDocument);

        customer.setDocuments(documents);

        assertEquals(documents, customer.getDocuments());
    }

    @Test
    @DisplayName("Get and set contacts")
    void setContacts() {
        CustomerContact customerDocument = new CustomerContact("john.doe@mail.com", "+5511999999999");
        Customer customer = new Customer();

        List<CustomerContact> contacts = new ArrayList<>();
        contacts.add(customerDocument);

        customer.setContacts(contacts);

        assertEquals(contacts, customer.getContacts());
    }

    @Test
    @DisplayName("Return both hash codes equals if customers are equal")
    void hashCode_whenBothCustomersAreEqual_ThenReturnHashEqual() {
        Customer customer1 = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        Customer customer2 = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        assertEquals(customer2.hashCode(), customer1.hashCode());
    }

    @Test
    @DisplayName("Return both hash codes different if customers are different")
    void hashCode_whenBothCustomersAreDifferent_ThenReturnHashDifferent() {
        Customer customer1 = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        Customer customer2 = new Customer(
                2L,
                "Maria Silva",
                "Segment 2",
                null,
                null
        );

        assertNotEquals(customer2.hashCode(), customer1.hashCode());
    }

    @Test
    @DisplayName("Return empty string if customer is null")
    void toString_whenObjectIsNull_ThenReturnEmptyString() {
        Customer customer = new Customer();

        assertThat(customer.toString())
                .isEqualTo("Customer{id=null, name='null', segmentName='null', documents=null, contacts=null}");
    }

    @Test
    @DisplayName("Return customer data")
    void toString_whenCustomerExists_ThenReturnCustomerData() {
        Customer customer = new Customer(
                1L,
                "John Doe",
                "Segment 1",
                null,
                null
        );

        assertThat(customer.toString())
                .isEqualTo(String.format("Customer{id=%d, name='%s', segmentName='%s', documents=%s, contacts=%s}", 1L, "John Doe", "Segment 1", "null", "null"));
    }


}