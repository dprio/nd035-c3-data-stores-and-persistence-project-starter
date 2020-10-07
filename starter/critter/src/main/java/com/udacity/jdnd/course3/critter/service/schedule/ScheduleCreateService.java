package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Service
@RequiredArgsConstructor
public class ScheduleCreateService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;

    public Schedule execute(final Schedule schedule){

        final List<Employee> employees = emptyIfNull(schedule.getEmployees()).stream()
                .map(employee -> employeeRepository.findById(employee.getId()))
                .filter(Objects::nonNull)
                .filter(employee ->
                                employee.getDaysAvailable().contains(schedule.getDate().getDayOfWeek()) &&
                                employee.getSkills().stream()
                                        .anyMatch(employeeSkill -> schedule.getActivities().contains(employeeSkill))
                        )
                .collect(Collectors.toList());

        schedule.setEmployees(employees);

        final List<Pet> pets = emptyIfNull(schedule.getPets()).stream()
                .map(pet -> petRepository.findById(pet.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        schedule.setPets(pets);

        return scheduleRepository.save(schedule);
    }

}
