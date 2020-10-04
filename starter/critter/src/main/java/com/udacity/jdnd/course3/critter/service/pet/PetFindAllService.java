package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetFindAllService {

    private final PetRepository petRepository;

    public List<Pet> execute(){
        return petRepository.findAll();
    }
}
