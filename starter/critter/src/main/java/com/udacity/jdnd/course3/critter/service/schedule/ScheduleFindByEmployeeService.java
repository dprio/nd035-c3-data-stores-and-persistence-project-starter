package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleFindByEmployeeService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    public List<Schedule> execute(final Employee employee){
        final Employee employee1 = employeeRepository.findById(employee.getId())
                .orElseThrow(EntityNotFoundException::new);

        return scheduleRepository.findByEmployee(employee1);
    }
}
