package com.udacity.jdnd.course3.critter.domain.pet;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    private String name;

    @ManyToOne(optional = false)
    private Customer owner;
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules;

    @Nationalized
    private String notes;
}
