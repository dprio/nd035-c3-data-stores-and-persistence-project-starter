package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetCreateService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public Long execute (final Pet pet){
        customerRepository.findById(pet.getOwner().getId()).ifPresent(pet::setOwner);
        return petRepository.save(pet);
    }
}
