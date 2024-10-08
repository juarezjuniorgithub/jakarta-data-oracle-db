/*
  Copyright (c) 2024, Oracle and/or its affiliates.

  This software is dual-licensed to you under the Universal Permissive License
  (UPL) 1.0 as shown at https://oss.oracle.com/licenses/upl or Apache License
  2.0 as shown at http://www.apache.org/licenses/LICENSE-2.0. You may choose
  either license.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

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
