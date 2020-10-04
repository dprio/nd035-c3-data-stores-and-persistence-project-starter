package com.udacity.jdnd.course3.critter.service.employee;

import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeCreateService {

    private final EmployeeRepository employeeRepository;

    public Long execute(final Employee employee){
        return employeeRepository.save(employee);
    }
}
