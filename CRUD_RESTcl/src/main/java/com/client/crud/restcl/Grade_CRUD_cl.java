/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.crud.restcl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saloni
 */
public class Grade_CRUD_cl {
    
    private static final Logger LOG = LoggerFactory.getLogger(Grade_CRUD_cl.class);
    
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/CRUD_RESTws/webresources";

    public Grade_CRUD_cl() {        
        LOG.info("Creating a Grade_CRUD REST client");

        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("Grade");
        LOG.debug("webResource = {}", webResource.getURI());
    }

    public ClientResponse createGrade(Object requestEntity) throws UniformInterfaceException {
        LOG.info("Initiating a Create request");
        LOG.debug("XML String = {}" +(String) requestEntity);
        
        return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse deleteGrade(String StudId, String GraName) throws UniformInterfaceException {
        LOG.info("Initiating a Delete request");
        LOG.debug("Student Id = {}", StudId);
        
        return webResource.path(StudId).path(GraName).delete(ClientResponse.class);
    }
    
    public ClientResponse deleteAllGrade(String StudId) throws UniformInterfaceException {
        LOG.info("Initiating a Delete All request");
        LOG.debug("Student Id = {}", StudId);
        
        return webResource.path(StudId).delete(ClientResponse.class);
    }

    public ClientResponse updateGrade(Object requestEntity, String StudId, String GraName) throws UniformInterfaceException {
        LOG.info("Initiating an Update request");
        LOG.debug("XML String = {}", (String) requestEntity);
        LOG.debug("Student Id = {}", StudId);
        
        return webResource.path(StudId).path(GraName).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);
    }

    public <T> T retrieveGrade(Class<T> responseType, String StudId, String GraName) throws UniformInterfaceException {
        LOG.info("Initiating a Retrieve request");
        LOG.debug("responseType = {}", responseType.getClass());
        LOG.debug("Student Id = {}", StudId);
        
        //WebResource resource = webResource;
        //resource = resource.path(id);
        
        return webResource.path("ReadingGradeItem").path(StudId).path(GraName).accept(MediaType.APPLICATION_XML).get(responseType);
    }
    
    public <T> T retrieveAllGrade(Class<T> responseType, String StudId) throws UniformInterfaceException {
        LOG.info("Initiating a Retrieve request");
        LOG.debug("responseType = {}", responseType.getClass());
        LOG.debug("Student Id = {}", StudId);
        
        LOG.debug("printing weresource {}" ,webResource);
        //WebResource resource = webResource;
        //resource = resource.path(id);
        
        return webResource.path(StudId).accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        LOG.info("Closing the REST Client");
        
        client.destroy();
    }
    
}
