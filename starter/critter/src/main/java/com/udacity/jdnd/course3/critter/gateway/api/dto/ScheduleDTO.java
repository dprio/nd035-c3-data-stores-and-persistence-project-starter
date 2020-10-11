package com.udacity.jdnd.course3.critter.gateway.api.dto;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.domain.user.EmployeeSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.ListUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@Builder
public class ScheduleDTO {
    private Long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public Schedule toDomain(){
        return Schedule.builder()
                .activities(this.activities)
                .date(this.date)
                .employees(emptyIfNull(this.employeeIds).stream().map(Employee::new).collect(toList()))
                .id(this.id)
                .pets(emptyIfNull(petIds).stream().map(petId -> Pet.builder().id(petId).build()).collect(toList()))
                .build();
    }

    public static ScheduleDTO fromSchedule(final Schedule schedule){
        return ScheduleDTO.builder()
                .activities(schedule.getActivities())
                .date(schedule.getDate())
                .employeeIds(emptyIfNull(schedule.getEmployees()).stream().map(Employee::getId).collect(toList()))
                .id(schedule.getId())
                .petIds(emptyIfNull(schedule.getPets()).stream().map(Pet::getId).collect(toList()))
                .build();
    }

    public static List<ScheduleDTO> fromScheduleList(final List<Schedule> schedules){
        return schedules.stream()
                .map(ScheduleDTO::fromSchedule)
                .collect(toList());
    }

}
