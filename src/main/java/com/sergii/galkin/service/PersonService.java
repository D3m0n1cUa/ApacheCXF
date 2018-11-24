package com.sergii.galkin.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sergii.galkin.model.Person;

@Path("/person")
public interface PersonService {

    // http://localhost:8080/ApacheCXF/service/person/get/nif/{nif}
    @GET
    @Path("get/nif/{nif}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByNIF(@PathParam("nif") String nif);

    // http://localhost:8080/ApacheCXF/service/person/get/name/{name}
    @GET
    @Path("get/name/{name}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersonByName(@PathParam("name") String name);

    // http://localhost:8080/ApacheCXF/service/person/getall
    @GET
    @Path("getall")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons();

    // http://localhost:8080/ApacheCXF/service/person/add
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addPerson(Person person);

    // http://localhost:8080/ApacheCXF/service/person/delete/{nif}
    @DELETE
    @Path("delete/{nif}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deletePerson(@PathParam("nif") String nif);

}
