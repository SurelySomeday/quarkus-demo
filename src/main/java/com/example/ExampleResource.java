package com.example;

import com.example.entity.Gift;
import com.example.service.SantaClausService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    SantaClausService service;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive3";
    }

    @GET
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public String create() {
        service.createGift("hello");
        return "ok";
    }

    @GET
    @Path("query")
    public List<Gift> query() {
        return service.query();
    }

}