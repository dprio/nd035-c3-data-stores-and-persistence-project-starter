package com.udacity.jdnd.course3.critter.domain.user;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

    private String phoneNumber;

    @Nationalized
    private String notes;

    @OneToMany
    private List<Pet> pets;
}
