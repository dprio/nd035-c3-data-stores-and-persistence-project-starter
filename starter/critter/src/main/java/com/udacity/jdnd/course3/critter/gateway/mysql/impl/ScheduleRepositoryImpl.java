package com.udacity.jdnd.course3.critter.gateway.mysql.impl;

import com.udacity.jdnd.course3.critter.domain.Schedule;
import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.gateway.mysql.ScheduleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private static final String FIND_ALL = "SELECT s from Schedule s";
    private static final String FIND_BY_PET =
            "SELECT s from Schedule s " +
                    "WHERE :pet MEMBER OF s.pets";
    private static final String FIND_BY_EMPLOYEE =
            "SELECT s from Schedule s " +
                    "WHERE :employee MEMBER OF s.employees";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Schedule save(final Schedule schedule) {
        entityManager.persist(schedule);
        return schedule;
    }

    @Override
    public List<Schedule> findAll() {
        final TypedQuery<Schedule> query = entityManager.createQuery(FIND_ALL, Schedule.class);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findByPet(final Pet pet) {
        final TypedQuery<Schedule> query = entityManager.createQuery(FIND_BY_PET, Schedule.class);
        query.setParameter("pet", pet);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findByEmployee(final Employee employee) {
        final TypedQuery<Schedule> query = entityManager.createQuery(FIND_BY_EMPLOYEE, Schedule.class);
        query.setParameter("employee", employee);
        return query.getResultList();
    }

}
