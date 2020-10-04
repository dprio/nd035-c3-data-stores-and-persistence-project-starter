package com.udacity.jdnd.course3.critter.gateway.api.dto;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@Builder
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public Customer toCustomerDomain(){
        return new Customer(
                this.name,
                this.phoneNumber,
                this.notes
        );
    }

    public static CustomerDTO fromDomain(final Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .notes(customer.getNotes())
                .phoneNumber(customer.getPhoneNumber())
                .petIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()))
                .build();
    }

    public static List<CustomerDTO> fromDomainList(final List<Customer> customers){
        return customers.stream()
                .map(CustomerDTO::fromDomain)
                .collect(Collectors.toList());
    }

}
