package com.sergii.galkin.repository;

import java.util.HashMap;
import java.util.Map;

import com.sergii.galkin.model.Person;

public class PersonRepository {

    private Map<String, Person> database = new HashMap<String, Person>();
    private static PersonRepository personRepository;

    private PersonRepository() {
	database.put("123", new Person("123", "Sergii", "Add1", "Phone1"));
	database.put("456", new Person("456", "Natalya", "Leiria", "Phone2"));
	database.put("789", new Person("789", "Sergii", "Add2", "Phone4"));
	database.put("1356", new Person("1356", "Natalya", "Lisbon", "Phone2123"));

    }

    public static PersonRepository getPersonRepository() {
	if (personRepository == null) {
	    personRepository = new PersonRepository();
	}

	return personRepository;
    }

    public Map<String, Person> getDataBase() {
	return database;
    }

}
