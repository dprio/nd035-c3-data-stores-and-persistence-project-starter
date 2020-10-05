package com.udacity.jdnd.course3.critter.gateway.api;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.gateway.api.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.pet.PetCreateService;
import com.udacity.jdnd.course3.critter.service.pet.PetFindAllService;
import com.udacity.jdnd.course3.critter.service.pet.PetFindByIdService;
import com.udacity.jdnd.course3.critter.service.pet.PetFindByOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
@ResponseBody
@RequiredArgsConstructor
public class PetController {

    private final PetCreateService petCreateService;
    private final PetFindAllService petFindAllService;
    private final PetFindByIdService petFindByIdService;
    private final PetFindByOwnerService petFindByOwnerService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        final Pet pet = petCreateService.execute(petDTO.toPetDomain());
        return PetDTO.fromPet(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        final Pet pet = petFindByIdService.execute(petId);
        return PetDTO.fromPet(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        final List<Pet> pets = petFindAllService.execute();
        return PetDTO.fromPetList(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        final List<Pet> pets = petFindByOwnerService.execute(ownerId);
        return PetDTO.fromPetList(pets);
    }
}
