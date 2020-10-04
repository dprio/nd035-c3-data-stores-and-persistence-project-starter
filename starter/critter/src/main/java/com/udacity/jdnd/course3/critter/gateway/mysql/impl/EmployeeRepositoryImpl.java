package com.udacity.jdnd.course3.critter.gateway.mysql.impl;

import com.udacity.jdnd.course3.critter.domain.user.Employee;
import com.udacity.jdnd.course3.critter.domain.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.gateway.mysql.EmployeeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String FIND_BY_DAY_AVAILABE =
            "SELECT e FROM Employee e " +
            "WHERE :dayOfWeek MEMBER OF e.daysAvailable";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(final Employee employee) {
        entityManager.persist(employee);
        return employee.getId();
    }

    @Override
    public Employee findById(final Long employeeId) {
        return entityManager.find(Employee.class,employeeId);
    }

    @Override
    public void setAvailability(final Long employeeId, final Set<DayOfWeek> daysAvailable) {
        final Employee persitedEmployee = entityManager.find(Employee.class, employeeId);
        persitedEmployee.setDaysAvailable(daysAvailable);
    }

    @Override
    public List<Employee> findByDayAvailableAndSkill(final DayOfWeek dayOfWeek, final Set<EmployeeSkill> skills) {
        final TypedQuery<Employee> query = entityManager.createQuery(FIND_BY_DAY_AVAILABE, Employee.class);
        query.setParameter("dayOfWeek", dayOfWeek);
        return query.getResultList().stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
