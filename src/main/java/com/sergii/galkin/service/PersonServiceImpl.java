package com.sergii.galkin.service;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergii.galkin.dao.PersonDAO;
import com.sergii.galkin.model.Person;
import com.sergii.galkin.validator.Validator;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public Response getPersonByNIF(String nif) {
	if (!Validator.validateNIF(nif)) {
	    return Response.status(Response.Status.BAD_REQUEST).build();
	}
	Person person = personDAO.getPersonByNIF(nif);
	if (person == null) {
	    return Response.status(Response.Status.NOT_FOUND).build();
	}

	return Response.ok(person).build();
    }

    @Override
    public Response getPersonByName(String name) {
	return Response.ok(personDAO.getPersonByName(name)).build();
    }

    @Override
    public Response getAllPersons() {
	return Response.ok(personDAO.getAllPersons()).build();
    }

    @Override
    public Response deletePerson(String nif) {
	boolean deleted = personDAO.deletePerson(nif);
	if (deleted) {
	    return Response.ok().build();
	}

	return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response addPerson(Person person) {
	boolean added = personDAO.addPerson(person);
	if (added == false) {
	    return Response.status(Response.Status.CONFLICT).build();
	}

	return Response.ok().build();
    }

}
