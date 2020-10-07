package com.udacity.jdnd.course3.critter.service.schedule;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleFindAllService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> execute(){
        return scheduleRepository.findAll();
    }
}
