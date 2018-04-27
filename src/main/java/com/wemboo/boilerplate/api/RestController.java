/**
 * This is a typical REST controller
 * Created by osh on 25/10/17.
 */

package com.wemboo.boilerplate.api;

import com.wemboo.boilerplate.beans.SessionStateBean;
import com.wemboo.boilerplate.db.*;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;

@Path("/")
public class RestController {

    @Inject
    private Logger log;
    
    @Inject
    private SessionStateBean bean;

    @Inject
    private SampleRepository sampleRepository;

    /**
     * Produces JSON
     */
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVehicleInfo(@QueryParam("greeting") String greeting) {

        log.info("We have received greeting string: "+greeting);
        if (greeting != null && greeting.equals("Hi!"))
            return bean.getGreeting();
        else    
            return ".";
    }



    /**
     * Uses {@linkplain SampleRepository} to produce JSON
     */
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SampleEntity> getSampleEntity() {
        log.info("Get SampleEntity list");

        List<SampleEntity> resultList = sampleRepository.findAll();

        log.info("SampleEntity list size: "+(resultList!=null ? resultList.size() : 0));

        return resultList;
    }


    /**
     * Shows how the {@linkplain POST} method consumes JSON
     * jquery client side should use JSON.stringify
     * {@linkplain Transactional} is required for persisting the object
     * @param context - is used to get {@linkplain Principal}
     */
    @Path("/postupdate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void insertSampleEntityFromPost(@Context SecurityContext context, SampleEntity sampleEntity) {

        log.info("POST: Check auth settings");
        Principal user = context.getUserPrincipal();
        if (user != null) {
            log.info("User = " + user.getName());
            sampleEntity.setName(user.getName());
        }

        sampleRepository.save(sampleEntity);
        
    }


    /**
     * Shows how the {@linkplain GET} method consumes query params
     * {@linkplain Transactional} is required for persisting the object
     * @param context - is used to get {@linkplain Principal}
     * @return 
     */
    @Path("/getupdate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public long insertSampleEntityFromGet(@Context SecurityContext context, @QueryParam("name") String name) {

        long newId = 0;

        log.info("GET: Check auth settings");
        Principal user = context.getUserPrincipal();
        if (user != null) {
            log.info("User = " + user.getName());

            SampleEntity sampleEntity = new SampleEntity();
            sampleEntity.setName(name);

            log.info("Insert SampleEntity: "+sampleEntity);
            log.info(">> SampleEntity name: "+sampleEntity.getName());
            log.info(">> SampleEntity id: "+sampleEntity.getRecordId());

            newId = sampleRepository.save(sampleEntity);            
            log.info("Inserted with Id: "+newId);
            
        }

        return newId;
    }    

}