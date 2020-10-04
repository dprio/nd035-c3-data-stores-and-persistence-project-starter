package com.udacity.jdnd.course3.critter.service.customer;

import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCreateService {

    private final CustomerRepository customerRepository;

    public Long execute(final Customer customer, final List<Long> petsIds){
        return customerRepository.save(customer);
    }
}
