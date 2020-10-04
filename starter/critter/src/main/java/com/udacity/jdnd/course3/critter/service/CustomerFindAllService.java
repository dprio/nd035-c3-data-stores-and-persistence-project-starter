package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerFindAllService {

    private final CustomerRepository customerRepository;

    public List<Customer> execute(){
        return customerRepository.findAll();
    }

}
