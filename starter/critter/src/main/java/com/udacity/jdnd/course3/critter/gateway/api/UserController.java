package com.udacity.jdnd.course3.critter.gateway.api;

import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.api.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.gateway.api.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.gateway.api.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.service.customer.CustomerCreateService;
import com.udacity.jdnd.course3.critter.service.customer.CustomerFindAllService;
import com.udacity.jdnd.course3.critter.service.customer.CustomerFindByPet;
import com.udacity.jdnd.course3.critter.service.employee.EmployeeCreateService;
import com.udacity.jdnd.course3.critter.service.employee.EmployeeFindByDayAvailable;
import com.udacity.jdnd.course3.critter.service.employee.EmployeeFindByIdService;
import com.udacity.jdnd.course3.critter.service.employee.EmployeeSetAvailabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CustomerCreateService customerCreateService;
    private final CustomerFindAllService customerFindAllService;
    private final CustomerFindByPet customerFindByPet;
    private final EmployeeCreateService employeeCreateService;
    private final EmployeeFindByIdService employeeFindByIdService;
    private final EmployeeSetAvailabilityService employeeSetAvailabilityService;
    private final EmployeeFindByDayAvailable employeeFindByDayAvailable;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        log.info("Starting create customer: {}", customerDTO);
        final Customer customer = customerDTO.toCustomerDomain();
        final Long customerID = customerCreateService.execute(customer, customerDTO.getPetIds());
        customerDTO.setId(customerID);

        log.info("Successfully created customer: {}", customerDTO);
        return customerDTO;
    }
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        log.info("Starting getAllCustomers");
        return CustomerDTO.fromDomainList(customerFindAllService.execute());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        final Customer customer = customerFindByPet.execute(petId);
        return CustomerDTO.fromDomain(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        final Long employeeId = employeeCreateService.execute(employeeDTO.toEmployeeDomain());
        employeeDTO.setId(employeeId);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        final Employee employee = employeeFindByIdService.execute(employeeId);
        return EmployeeDTO.fromEmployee(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeSetAvailabilityService.execute(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        final List<Employee> employees = employeeFindByDayAvailable
                .execute(employeeDTO.getDate().getDayOfWeek(), employeeDTO.getSkills());
        return EmployeeDTO.fromEmployeeList(employees);
    }

}
