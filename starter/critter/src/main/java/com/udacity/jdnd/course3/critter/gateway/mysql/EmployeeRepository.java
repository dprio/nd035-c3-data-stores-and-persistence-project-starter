package com.udacity.jdnd.course3.critter.gateway.mysql;

import com.udacity.jdnd.course3.critter.domain.user.Employee;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository {

    Long save(Employee employee);

    Employee findById(Long employeeId);

    void setAvailability(Long employeeId, Set<DayOfWeek> daysAvailable);

    List<Employee> findByDaysAvailable(DayOfWeek dayOfWeek);
}