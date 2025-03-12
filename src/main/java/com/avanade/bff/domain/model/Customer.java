package com.avanade.bff.domain.model;

import java.util.List;
import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String segmentName;
    private List<CustomerDocument> documents;
    private List<CustomerContact> contacts;

    public Customer(Long id,
                    String name,
                    String segmentName,
                    List<CustomerDocument> documents,
                    List<CustomerContact> contacts) {
        this.id = id;
        this.name = name;
        this.segmentName = segmentName;
        this.documents = documents;
        this.contacts = contacts;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public List<CustomerDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<CustomerDocument> documents) {
        this.documents = documents;
    }

    public List<CustomerContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<CustomerContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", segmentName='" + segmentName + '\'' +
                ", documents=" + documents +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(segmentName, customer.segmentName) &&
                Objects.equals(documents, customer.documents) &&
                Objects.equals(contacts, customer.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, segmentName, documents, contacts);
    }
}
