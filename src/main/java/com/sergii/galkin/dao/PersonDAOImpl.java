package com.sergii.galkin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sergii.galkin.model.Person;
import com.sergii.galkin.validator.PersonValidation;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Person getPersonByNIF(String nif) {
	Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, nif);
	return person != null ? person : null;
    }

    @Transactional
    @Override
    public List<Person> getPersonByName(String name) {
	Criteria query = sessionFactory.getCurrentSession().createCriteria(Person.class);
	query.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
	List<Person> persons = query.list();
	return persons;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Person> getAllPersons() {
	return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
    }

    @Transactional
    @Override
    public boolean deletePerson(String nif) {
	Person person = getPersonByNIF(nif);
	if (person != null) {
	    sessionFactory.getCurrentSession().delete(person);
	    return true;
	}
	return false;
    }

    @Transactional
    @Override
    public boolean addPerson(Person person) throws ConstraintViolationException {
	if (!PersonValidation.validatePerson(person) || getPersonByNIF(person.getNif()) != null) {
	    return false;
	}
	sessionFactory.getCurrentSession().save(person);

	return true;
    }

}
