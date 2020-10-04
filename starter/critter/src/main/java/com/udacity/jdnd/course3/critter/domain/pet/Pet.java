package com.udacity.jdnd.course3.critter.domain.pet;

import com.udacity.jdnd.course3.critter.domain.pet.PetType;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue
    private long id;
    private PetType type;
    private String name;

    @ManyToOne
    private Customer owner;
    private LocalDate birthDate;

    @Nationalized
    private String notes;
}
