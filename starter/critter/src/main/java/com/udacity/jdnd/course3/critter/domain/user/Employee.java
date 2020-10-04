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

    @ManyToMany
    private List<Schedule> schedules;

}
