package com.udacity.jdnd.course3.critter.domain.user;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends User{

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    public Employee(final String name, final Set<EmployeeSkill> skills, final Set<DayOfWeek> daysAvailable){
        super(null, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee(final Long id){
        super(id, null);
    }

}
