package com.udacity.jdnd.course3.critter.service.employee;

import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeSetAvailabilityService {

    private final EmployeeRepository employeeRepository;

    public void execute(final Long employeeId, final Set<DayOfWeek> daysAvailable){
        employeeRepository.setAvailability(employeeId, daysAvailable);
    }
}
