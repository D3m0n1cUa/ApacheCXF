package com.sergii.galkin.test;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.sergii.galkin.model.Person;

public class TestResponse {
    private Status status;
    private List<Person> persons;

    public TestResponse(Status status, List<Person> persons) {
	this.status = status;
	this.persons = persons;
    }

    public TestResponse(Status status, Person person) {
	this(status);
	if (person == null) {
	    this.persons = null;
	} else {
	    this.persons = Arrays.asList(person);
	}
    }

    public TestResponse(Status status) {
	this.status = status;
	this.persons = null;
    }

    public Status getStatus() {
	return status;
    }

    public List<Person> getPersons() {
	return persons;
    }

    @Override
    public String toString() {
	return "TestResponse [status=" + status + ", persons=" + persons + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((persons == null) ? 0 : persons.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TestResponse other = (TestResponse) obj;
	if (persons == null) {
	    if (other.persons != null)
		return false;
	} else if (!persons.equals(other.persons))
	    return false;
	if (status != other.status)
	    return false;
	return true;
    }

    public static TestResponse internalError() {
	return new TestResponse(Status.fromStatusCode(500));
    }
}
