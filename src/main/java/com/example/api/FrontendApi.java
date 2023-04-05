package com.example.api;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author yanxin
 * @Description:
 */
@RequestScoped
@Path("/api")
public class FrontendApi {

    @RestClient
    MyService service;
    @ConfigProperty(name="name")
    private Provider<String> name;

    @Inject
    private Vertx vertx;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String invoke() {
        return service.get();
    }

    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String name() {
        return name.get();
    }


}