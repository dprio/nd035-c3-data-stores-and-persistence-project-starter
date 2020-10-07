package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleFindByPetService {

    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;

    public List<Schedule> execute(final Pet pet) {
        final Pet pet1 = petRepository.findById(pet.getId())
                .orElseThrow(EntityNotFoundException::new);

        return scheduleRepository.findByPet(pet1);
    }
}
