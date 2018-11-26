package com.sergii.galkin.dao;

import java.util.List;

import com.sergii.galkin.model.Person;

public interface PersonDAO {

    public Person getPersonByNIF(String nif);

    public List<Person> getPersonByName(String name);

    public List<Person> getAllPersons();

    public boolean deletePerson(String nif);

    public boolean addPerson(Person person);

}
