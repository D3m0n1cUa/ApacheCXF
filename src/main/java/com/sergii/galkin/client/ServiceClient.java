package com.sergii.galkin.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import com.sergii.galkin.model.Person;
import com.sergii.galkin.test.TestResponse;

//import com.sergii.galkin.model.Person;
//import com.sergii.galkin.test.TestResponse;

public class ServiceClient {

    private static final int INTERNAL_SERVER_ERROR = 500;
    private static String httpRequestGetPersonByNif = "http://localhost:8080/ApacheCXF/services/person/get/nif/";

    private static String httpRequestGetPersonByName = "http://localhost:8080/ApacheCXF/services/person/get/name/";

    public TestResponse getPersonByNif(String nif) {
	URL url = null;
	try {
	    url = new URL(httpRequestGetPersonByNif + nif);
	    HttpURLConnection httpURLConnection = getHttpURLConnection(url);
	    String jsonInput = getJSON(httpURLConnection);
	    Person person = getPersonFromJSON(jsonInput);
	    return new TestResponse(Status.fromStatusCode(httpURLConnection.getResponseCode()), person);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return TestResponse.internalError();

    }

    public TestResponse getPersonByName(String name) {
	URL url = null;
	List<Person> persons = null;
	try {
	    url = new URL(httpRequestGetPersonByName + name);
	    HttpURLConnection httpURLConnection = getHttpURLConnection(url);
	    String jsonInput = getJSON(httpURLConnection);
	    persons = getPersonsFromJSON(jsonInput);
	    return new TestResponse(Status.fromStatusCode(httpURLConnection.getResponseCode()), persons);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return TestResponse.internalError();
    }

    private Person getPersonFromJSON(String jsonInput) {
	if (jsonInput == null) {
	    return null;
	}
	Person person = null;

	ObjectMapper mapper = new ObjectMapper();
	try {
	    person = mapper.readValue(jsonInput, Person.class);
	    return person;
	} catch (IOException e) {
	    //e.printStackTrace();
	    return null;
	}
    }

    private List<Person> getPersonsFromJSON(String jsonInput) {
	List<Person> persons = Collections.emptyList();

	ObjectMapper mapper = new ObjectMapper();
	try {
	    persons = mapper.readValue(jsonInput,
		    mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return persons;
    }

    private HttpURLConnection getHttpURLConnection(URL url) {
	HttpURLConnection httpURLConnection = null;
	try {
	    httpURLConnection = (HttpURLConnection) url.openConnection();
	    httpURLConnection.setRequestMethod(HttpMethod.GET);
	    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    httpURLConnection.setRequestProperty("Accept", "application/json");
	    httpURLConnection.setDoOutput(true);

	    return httpURLConnection;

	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	    httpURLConnection.disconnect();
	}

	return null;
    }

    private String getJSON(HttpURLConnection httpURLConnection) {
	try (InputStreamReader stream = new InputStreamReader(httpURLConnection.getInputStream())) {
	    BufferedReader in = new BufferedReader(stream);
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	    }
	    in.close();
	    return response.toString();
	} catch (IOException e) {
	    //e.printStackTrace();
	}

	return null;
    }
}
