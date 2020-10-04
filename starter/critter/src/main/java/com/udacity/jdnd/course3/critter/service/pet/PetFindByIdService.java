package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PetFindByIdService {

    private final PetRepository petRepository;

    public Pet execute(final Long petId){
        return petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
