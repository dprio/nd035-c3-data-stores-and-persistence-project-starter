package com.udacity.jdnd.course3.critter.gateway.api.dto;

import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.domain.user.EmployeeSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@Builder
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public Employee toEmployeeDomain(){
        return new Employee(
                this.name,
                this.skills,
                this.daysAvailable
        );
    }

    public static EmployeeDTO fromEmployee(final Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .skills(employee.getSkills())
                .daysAvailable(employee.getDaysAvailable())
                .build();
    }

    public static List<EmployeeDTO>  fromEmployeeList(final List<Employee> employees){
        return employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

}
