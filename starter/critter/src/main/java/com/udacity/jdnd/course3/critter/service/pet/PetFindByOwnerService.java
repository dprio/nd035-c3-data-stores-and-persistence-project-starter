package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetFindByOwnerService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public List<Pet> execute(final Long ownerId){
        return customerRepository.findById(ownerId)
                .map(petRepository::findByOwner)
                .orElseThrow(EntityNotFoundException::new);

    }
}
