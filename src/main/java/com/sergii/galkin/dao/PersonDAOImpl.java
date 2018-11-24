package com.sergii.galkin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sergii.galkin.model.Person;
import com.sergii.galkin.repository.PersonRepository;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public Person getPersonByNIF(String nif) {
	PersonRepository repository = PersonRepository.getPersonRepository();
	return repository.getDataBase().get(nif);
    }

    @Override
    public List<Person> getPersonByName(String name) {
	PersonRepository repository = PersonRepository.getPersonRepository();
	List<Person> persons = new ArrayList();
	for (Map.Entry<String, Person> person : repository.getDataBase().entrySet()) {
	    if (name.equals(person.getValue().getName())) {
		persons.add(person.getValue());
	    }
	}
	return persons;
    }

    @Override
    public List<Person> getAllPersons() {
	PersonRepository repository = PersonRepository.getPersonRepository();
	List<Person> persons = new ArrayList(repository.getDataBase().values());
	return persons;
    }

    @Override
    public boolean deletePerson(String nif) {
	PersonRepository repository = PersonRepository.getPersonRepository();
	if (repository.getDataBase().containsKey(nif)) {
	    repository.getDataBase().remove(nif);
	    return true;
	}

	return false;
    }

    @Override
    public void addPerson(Person person) {
	PersonRepository repository = PersonRepository.getPersonRepository();
	repository.getDataBase().put(person.getNif(), person);
    }

}
