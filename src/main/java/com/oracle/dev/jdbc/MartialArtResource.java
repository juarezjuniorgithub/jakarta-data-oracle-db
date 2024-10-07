package com.oracle.dev.jdbc;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/martialarts")
public class MartialArtResource {

  @Inject
  private MartialArtRepository martialArtRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addMartialArt(MartialArt martialArt) {
    return Response.ok(
        "Added martial art: " + martialArtRepository.save(martialArt).getId())
        .build();
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response deleteById(@PathParam("id") Long id) {
    martialArtRepository.deleteById(id);
    return Response.ok("Deleted martial art with ID: " + id).build();
  }

  @GET
  @Path("/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String findOne(@PathParam("name") String name) {
    return martialArtRepository.findByNameIgnoreCase(name)
        .map(MartialArt::getCountry).orElse(name + " not found");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<MartialArt> findAll() {
    return martialArtRepository.findAll().toList();
  }

}