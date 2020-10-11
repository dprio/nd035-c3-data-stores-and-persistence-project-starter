package com.udacity.jdnd.course3.critter.gateway.mysql.impl;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String FIND_ALL = "SELECT c from Customer c left join fetch c.pets";
    private static final String FIND_BY_PET_ID =
            "SELECT c from Customer c " +
            "WHERE :pet MEMBER OF c.pets";


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(final Customer customer) {
        entityManager.persist(customer);
        return customer.getId();
    }

    @Override
    public List<Customer> findAll() {
        final TypedQuery<Customer> query = entityManager.createQuery(FIND_ALL, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findByPet(final Pet pet) {
        final TypedQuery<Customer> query = entityManager.createQuery(FIND_BY_PET_ID, Customer.class);
        query.setParameter("pet", pet);
        return query.getSingleResult();
    }

    @Override
    public Optional<Customer> findById(final Long customerId) {
        return Optional.ofNullable(entityManager.find(Customer.class, customerId));
    }
}
