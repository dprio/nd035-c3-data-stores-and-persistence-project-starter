package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Service
@RequiredArgsConstructor
public class ScheduleFindByCustomerService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public List<Schedule> execute(final Customer customer){
        final List<Pet> pets = customerRepository.findById(customer.getId())
                .map(petRepository::findByOwner)
                .orElseThrow(EntityNotFoundException::new);

        return emptyIfNull(pets).stream()
                .flatMap(pet -> scheduleRepository.findByPet(pet).stream())
                .collect(Collectors.toList());

    }
}
