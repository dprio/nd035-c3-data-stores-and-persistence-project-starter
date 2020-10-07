package com.udacity.jdnd.course3.critter.gateway.api;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.api.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.schedule.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
@ResponseBody
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleCreateService scheduleCreateService;
    private final ScheduleFindAllService scheduleFindAllService;
    private final ScheduleFindByPetService scheduleFindByPetService;
    private final ScheduleFindByEmployeeService scheduleFindByEmployeeService;
    private final ScheduleFindByCustomerService scheduleFindByCustomerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        final Schedule schedule = scheduleCreateService.execute(scheduleDTO.toDomain());
        return ScheduleDTO.fromSchedule(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        final List<Schedule> schedules = scheduleFindAllService.execute();
        return ScheduleDTO.fromScheduleList(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        final List<Schedule> schedules = scheduleFindByPetService.execute(Pet.builder().id(petId).build());
        return ScheduleDTO.fromScheduleList(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        final List<Schedule> schedules = scheduleFindByEmployeeService.execute(new Employee(employeeId));
        return ScheduleDTO.fromScheduleList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        final List<Schedule> schedules = scheduleFindByCustomerService.execute(new Customer(customerId));
        return ScheduleDTO.fromScheduleList(schedules);
    }
}
