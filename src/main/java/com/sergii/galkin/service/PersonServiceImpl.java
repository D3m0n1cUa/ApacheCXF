package com.sergii.galkin.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.sergii.galkin.dao.PersonDAO;
import com.sergii.galkin.dao.PersonDAOImpl;
import com.sergii.galkin.model.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private PersonDAO personDAO = new PersonDAOImpl();

    @Override
    public Person getPerson(String nif) {
	return personDAO.getPersonByNIF(nif);
    }

    @Override
    public List<Person> getAllPersons() {
	return personDAO.getAllPersons();
    }

    @Override
    public Response deletePerson(String nif) {
	boolean deleted = personDAO.deletePerson(nif);
	if (deleted) {
	    return Response.ok().build();
	}

	return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response addPerson(Person person) {
	if (person == null) {
	    return Response.status(Response.Status.BAD_REQUEST).build();
	}

	personDAO.addPerson(person);

	return Response.ok().build();
    }

}
