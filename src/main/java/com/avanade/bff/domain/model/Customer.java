package com.avanade.bff.domain.model;

import java.util.List;

public record Customer(Long id,
                       String name,
                       String segmentName,
                       List<CustomerDocument> documents,
                       List<CustomerContact> contacts) {
}
