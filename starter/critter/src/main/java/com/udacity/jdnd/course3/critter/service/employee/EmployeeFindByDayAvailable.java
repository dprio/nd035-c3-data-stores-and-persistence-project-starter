package com.udacity.jdnd.course3.critter.service.employee;

import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.domain.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeFindByDayAvailable {

    private final EmployeeRepository employeeRepository;

    public List<Employee> execute(final DayOfWeek dayOfWeek, final Set<EmployeeSkill> skills){
        return employeeRepository.findByDayAvailableAndSkill(dayOfWeek, skills);
    }
}
