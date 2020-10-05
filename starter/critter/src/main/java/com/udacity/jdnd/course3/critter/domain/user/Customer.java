package com.udacity.jdnd.course3.critter.domain.user;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.CascadeType;
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer(final String name, final String phoneNumber, final String notes){
        super(null, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer(final Long id){
        super(id, null);
    }
}
