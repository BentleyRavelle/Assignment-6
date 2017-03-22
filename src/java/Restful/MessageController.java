/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restful;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author c0636081
 */
@Path("/message")
@RequestScoped
public class MessageController {

    @PersistenceContext(unitName = "sampleproductPU")
    EntityManager em;

    @Inject
    UserTransaction transaction;

    List<Message> messageList;

    @GET
    @Produces("application/json")
    public Response getAll() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        Query q = em.createNamedQuery("Message.findAll");
        messageList = q.getResultList();
        for (Message p : messageList) {
            json.add(p.toJSON());
        }
        return Response.ok(json.build().toString()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") int id) {
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.messageId = :MessageId");
        q.setParameter("productId", id);
        Message m = (Message) q.getSingleResult();
        return Response.ok(m.toJSON().toString()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(JsonObject json) {
        Response result;
        try {
            transaction.begin();
            Message m = new Message(json);
            em.persist(m);
            transaction.commit();
            result = Response.ok().build();
        } catch (Exception ex) {
            result = Response.status(500).entity(ex.getMessage()).build();
        }
        return result;
    }

    @PUT
    @Consumes("application/json")
    public Response edit(JsonObject json) {
        Response result;
        try {
            transaction.begin();
            Message m = (Message) em.createNamedQuery("Message.findByMessageId")
                    .setParameter("messageId", json.getInt("messageId")).getSingleResult();
            m.setTitle(json.getString("Title"));
            m.setContent(json.getString("Content"));
            m.setAuthor(json.getString("author"));
            m.setSentTime(json.getString("sentTime"));
            em.persist(m);
            transaction.commit();
            result = Response.ok().build();
        } catch (Exception ex) {
            result = Response.status(500).entity(ex.getMessage()).build();
        }
        return result;
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        Response result;
        try {
            transaction.begin();
            Message m = (Message) em.createNamedQuery("Message.findByProductId")
                    .setParameter("messageId", id).getSingleResult();
            em.remove(m);
            transaction.commit();
            result = Response.ok().build();
        } catch (Exception ex) {
            result = Response.status(500).entity(ex.getMessage()).build();
        }
        return result;
    }

    }