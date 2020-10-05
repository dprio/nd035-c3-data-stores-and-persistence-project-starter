package com.udacity.jdnd.course3.critter.gateway.api.dto;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.pet.PetType;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Getter
@Setter
@Builder
public class PetDTO {
    private Long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public Pet toPetDomain(){
        return Pet.builder()
                .id(this.id)
                .type(this.type)
                .name(this.name)
                .birthDate(this.birthDate)
                .notes(this.notes)
                .owner(new Customer(this.ownerId))
                .build();
    }

    public static PetDTO fromPet(final Pet pet){
        return PetDTO.builder()
                .birthDate(pet.getBirthDate())
                .id(pet.getId())
                .name(pet.getName())
                .notes(pet.getNotes())
                .ownerId(pet.getOwner().getId())
                .type(pet.getType())
                .build();
    }

    public static List<PetDTO> fromPetList(final List<Pet> pets){
        return pets.stream()
                .map(PetDTO::fromPet)
                .collect(Collectors.toList());
    }

}
