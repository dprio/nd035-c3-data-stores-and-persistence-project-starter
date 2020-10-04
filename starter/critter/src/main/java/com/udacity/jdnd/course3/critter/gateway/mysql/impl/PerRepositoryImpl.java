package com.udacity.jdnd.course3.critter.gateway.mysql.impl;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PerRepositoryImpl implements PetRepository {

    private static final String FIND_ALL = "SELECT p from Pet p";
    private static final String FIND_BY_OWNER = "SELECT p from Pet p WHERE p.owner = :owner";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(final Pet pet) {
        entityManager.persist(pet);
        return pet.getId();
    }

    @Override
    public List<Pet> findAll() {
        final TypedQuery<Pet> query = entityManager.createQuery(FIND_ALL, Pet.class);
        return query.getResultList();
    }

    @Override
    public Pet findById(Long petId) {
        return entityManager.find(Pet.class, petId);
    }

    @Override
    public List<Pet> findByOwner(Customer customer) {
        final TypedQuery<Pet> query = entityManager.createQuery(FIND_BY_OWNER, Pet.class);
        query.setParameter("owner", customer);
        return query.getResultList();
    }
}
