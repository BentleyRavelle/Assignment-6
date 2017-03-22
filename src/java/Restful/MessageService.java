/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restful;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author c0636081
 */ 
@Path("/message")
@RequestScoped
public class MessageService {
    @GET
    @Produces("application/json")
    public JsonObject getJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("messageId", 1);
        json.add("title", "Sample Text");
        json.add("content", "Some sample content");
        json.add("author", "A. Sample");
        json.add("sentTime", "2016-03-31T13:24:11.135Z");
        return json.build();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonObject postJson(JsonObject json) {
        System.out.println(json.getInt("messageId", 1));
        System.out.println(json.getString("title", "Sample Title"));
        System.out.println(json.getString("contents", "Some sample content"));
        System.out.println(json.getString("author", "A. Sample"));
        System.out.println(json.getString("sentTime", "2016-03-31T13:24:11.135Z"));
        return json;
    }
    
    
    
    
    
    
    
    
    
    
}
