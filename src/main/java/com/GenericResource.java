package com;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("quote")
public class GenericResource {

    @Context
    private UriInfo context;
    private Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };

    /**
     * Retrieves representation of an instance of packag.GenericResource
     *
     * @param id
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("{id}")   
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") int id) {
        //TODO return proper representation object
        String str = quotes.get(id);
        String jsonStr = gson.toJson(str);
        return jsonStr;  
    }
    
    
    //Skal omskrives, så det tilfældige tal afhænger af antallet af quotes.
    @GET
    @Path("random")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandom() {
        System.out.println("random blev kaldt");
        //TODO return proper representation object
        Random rand = new Random();
        return quotes.get(rand.nextInt(3)+1);
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void updateQuote(String msg, int id) {
        quotes.replace(id, msg);
    }   
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteQuote(int id) {
        quotes.remove(id);
        return "Quote Deleted";
    }
     
    
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String makeQuote(String msg) {
        int id2 = quotes.size()+1;
        quotes.put(id2, msg);
        return "Added quote";
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}


