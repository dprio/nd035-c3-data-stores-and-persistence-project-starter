package com.udacity.jdnd.course3.critter.service.customer;

import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreateService {

    private final CustomerRepository customerRepository;

    public Customer execute(final Customer customer){
        return customerRepository.save(customer);
    }
}
