/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HEG Arc.
 */

package ch.hearc.ig.odi.minishop.restresources;

import ch.hearc.ig.odi.minishop.business.Customer;
import ch.hearc.ig.odi.minishop.exception.CustomerException;
import ch.hearc.ig.odi.minishop.services.PersistenceService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CustomerResource {

  @Inject
  private PersistenceService persistenceService;

  @GET
  public List<Customer> getCustomers() {
    return persistenceService.getAllCustomers();
  }

  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") Long id) throws CustomerException {
    return persistenceService.getCustomerByID(id);
  }

  @POST
  public void addCustomer(@FormParam("username") String username,
      @FormParam("firstname") String firstname,
      @FormParam("lastname") String lastname,
      @FormParam("email") String email,
      @FormParam("phone") String phone) {
    persistenceService.createAndPersistCustomer(username, firstname, lastname, email, phone);
  }

  @DELETE
  @Path("{id}")
  public void deleteCustomer(@PathParam("id") Long id) throws CustomerException {
    persistenceService.deleteCustomer(id);
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateCustomer(@PathParam("id") Long id, Customer customer) throws CustomerException {
    persistenceService.updateCustomer(id, customer);
  }


}

