package com.sergii.galkin.service;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.sergii.galkin.client.ServiceClient;
import com.sergii.galkin.model.Person;

public class PersonServiceTest {
    private static ServiceClient client = new ServiceClient();

    @Test
    public void getPersonByNIF() throws Exception {
	Person person = new Person("284111244", "Sergii Galkin", "Rua Gomes Freire 6 1dt", "+915304432");
	TestResponse responseExpect = new TestResponse(Status.fromStatusCode(200), person);
	TestResponse result = client.getPersonByNif(person.getNif());
	assertEquals(responseExpect, result);
    }

    @Test
    public void getPersonByNIFNotFound() throws Exception {
	TestResponse responseExpect = new TestResponse(Status.fromStatusCode(404));
	TestResponse result = client.getPersonByNif("284111314");
	assertEquals(responseExpect, result);
    }

    @Test
    public void getPersonByNIFBadRequest() throws Exception {
	TestResponse responseExpect = new TestResponse(Status.fromStatusCode(400));
	TestResponse result = client.getPersonByNif("284111sdfdf314");
	assertEquals(responseExpect, result);
    }

}
