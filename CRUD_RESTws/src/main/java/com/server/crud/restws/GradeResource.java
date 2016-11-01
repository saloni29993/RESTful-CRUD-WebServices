/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.crud.restws;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;  
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Web Service
 *
 * @author saloni
 */
@Path("/Grade")
public class GradeResource {

    private static final Logger LOG = LoggerFactory.getLogger(GradeResource.class);
    public static Gradebook newgradebook = new Gradebook();
    public static List<Student> studentlist = new ArrayList<Student>();
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GradeResource
     */
    public GradeResource() {
    }

    /**
     * Retrieves representation of an instance of com.server.crud.restws.GradeResource
     * @return an instance of java.lang.String
     */
    
    //For each student creating gradeitem.
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createResource(String content) {
        Grade newgrade = null;
        LOG.info("Creating the instance Grade Resource {}", content);
        LOG.debug("POST request");
        LOG.debug("Request Content = {}", content);
        
        Response response;
        
            try {
                LOG.debug("Inside For {}",content);
                
                newgrade = (Grade) Converter.convertFromXmlToObject(content, Grade.class);
                LOG.debug("Inside For {}",newgrade);
                /*Iterator it=studentlist.iterator();
                while ( it.hasNext())
                {
                    int stuid = (int)it.next().;
                    if(newgrade.Id == )
                }*/
                boolean flag = false;
                for(int i = 0; i < studentlist.size(); i++)
                {
                    if(studentlist.get(i).getId() == newgrade.getId())
                    {
                        Student s1 = studentlist.get(i);
                        for(int j = 0; j < s1.marks.size(); j++)
                        {
                            if(s1.marks.get(j).gradeitem.equals(newgrade.gradeitem))
                            {
                                flag = true;
                                break;
                            }
                        }
                        break;
                    }
                }
                
                if(flag)
                {
                        LOG.info("Creating a {} {} Status Response", Response.Status.CONFLICT.getStatusCode(), Response.Status.CONFLICT.getReasonPhrase());
                        LOG.debug("Cannot create Grade because Grade item already exists. {}", newgrade);
                      
                        response = Response.status(Response.Status.CONFLICT).entity(content).build();
                }
                else
                {
                    flag = false;
                    for(int i = 0; i < studentlist.size(); i++)
                    {
                        if(studentlist.get(i).getId() == newgrade.getId())
                        {
                            flag = true;
                            Student s1 = studentlist.get(i);
                            s1.marks.add(newgrade);
                            break;
                        }
                    }
                    
                    if(!flag)
                    {
                        Student s2 = new Student();
                        s2.setId(newgrade.Id);
                        s2.marks.add(newgrade);
                        studentlist.add(s2);
                    }
                    LOG.debug("before");
                    String xmlString = Converter.convertFromObjectToXml(newgrade, Grade.class);
                    //LOG.debug("after {}",newgradebook.gradebook.size());
                    URI locationURI = URI.create(context.getAbsolutePath() + "/" + newgrade.Id+"/"+newgrade.gradeitem);
            
                    response = Response.status(Response.Status.CREATED).location(locationURI).entity(xmlString).build();
            
                }    
                } catch (JAXBException e) {
                LOG.info("Creating a {} {} Status Response", Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase());
                LOG.debug("XML is {} is incompatible with Appointment Resource", content);
                
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
                LOG.debug("Catch All exception");
                
                LOG.info("Creating a {} {} Status Response", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
                e.printStackTrace();
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
            
        LOG.debug("Generated response {}", response);
        
        return response;
    }
    
    
//    //Getting the ID and displaying all info about that student
//    @GET
//    @Path("{StudId}")
//    @Produces(MediaType.APPLICATION_XML)
//    public Response getResource(@PathParam("StudId") String StudId) {
//       // LOG.info("Retrieving the Grade Resource {}", newgrade);
//        LOG.debug("GET request");
//        LOG.debug("PathParam id = {}  {} hiee", StudId,newgradebook.gradebook.size());
//        int StudId1=Integer.parseInt(StudId);
//        Response response;
//        Student newStud=null;
//            
//        for(int i=0; i<newgradebook.gradebook.size() ;i++)
//        {
//            LOG.debug("i = {}",i);
//             LOG.debug("Started Searching for student");
//            newStud=newgradebook.gradebook.get(i);
//            if(newStud.id==StudId1)
//            {
//                break;
//            }
//            else
//            {
//                newStud=null;
//            }
//        }
//        if (newStud == null){
//            LOG.info("Creating a {} {} Status Response", Response.Status.GONE.getStatusCode(), Response.Status.GONE.getReasonPhrase());
//            LOG.debug("No Grade Resource to return");
//            
//            response = Response.status(Response.Status.GONE).entity("No Appointment Resource to return").build();
//        } else {
//            LOG.debug("appointment.getId() = {}",newStud.id);
//             LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
//                LOG.debug("Retrieving the Appointment Resource {}", newStud);
//                String xmlString = Converter.convertFromObjectToXml(newStud, Student.class);
//                response = Response.status(Response.Status.OK).entity(xmlString).build();
//        }        
//        LOG.debug("Returning the value {}", response);
//        return response;
//    }
    
    
    //Getting the student ID and the item which he wants to see and displaying only that
    @GET
    @Path("ReadingGradeItem/{StudId}/{GradeItem}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getResource(@PathParam("StudId") String StudId,
                                @PathParam("GradeItem") String GradeItem) {
        LOG.debug("GET request");
        LOG.debug("PathParam id = {}", StudId);
        LOG.debug("PathParam grade item = {} {}", GradeItem,studentlist.get(0).marks.size());
        int StudId1=Integer.parseInt(StudId);
        
        Response response;
        Student newStud=null;
        Grade newGrade=null;
            
        for(int i=0; i<studentlist.size() ;i++)
        {
            Student s3 = studentlist.get(i);
            if(s3.id == Integer.parseInt(StudId))
            {
                LOG.debug("our     "+i);
                for(int j = 0; j < s3.marks.size();j++)
                {
                    LOG.debug("Yo     "+j);
                    if(s3.marks.get(j).gradeitem.equals(GradeItem))
                    {
                        LOG.debug("Inside");
                        newGrade = s3.marks.get(j);
                    }
                }
            }
        }    
         
        if (newGrade == null){
            LOG.info("Creating a {} {} Status Response", Response.Status.NOT_FOUND.getStatusCode(), Response.Status.GONE.getReasonPhrase());
            LOG.debug("No Grade Resource to return");
            
            response = Response.status(Response.Status.NOT_FOUND).entity("No Appointment Resource to return").build();
        } else {
                //LOG.debug("appointment.getId() = {}",newStud.id);
                LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
                LOG.debug("Retrieving the Appointment Resource {}", newGrade);
                String xmlString = Converter.convertFromObjectToXml(newGrade, Grade.class);
                response = Response.status(Response.Status.OK).entity(xmlString).build();
            
        }        
        LOG.debug("Returning the value {}", response);
        return response;
    }
     
    
        //Getting the student ID and the item which he wants to see and displaying only that
    @GET
    @Path("{StudId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllResource(@PathParam("StudId") String StudId) {
        LOG.debug("GET All request");
        LOG.debug("PathParam id = {}", StudId);
        //LOG.debug("PathParam grade item = {}",studentlist.get(0).marks.size());
        int StudId1=Integer.parseInt(StudId);
        
        
        Response response;
        Student newStud=null;
        Grade newGrade=null;
        List<Grade> newGrades = new ArrayList<Grade>(); 
        int pos=0;
        for(int i=0; i<studentlist.size() ;i++)
        {
            Student s3 = studentlist.get(i);
            
            if(s3.getId() == Integer.parseInt(StudId))
            {
              pos = i;  
                newGrades= studentlist.get(i).marks;
//                LOG.debug("our     "+i);
//                for(int j = 0; j < s3.marks.size();j++)
//                {
//                    LOG.debug("get All Grades "+j);
//                   
//                   
//                    LOG.debug("get all grades");
//                    newGrade = s3.marks.get(j);
//                    newGrades.add(newGrade);
                     
//                }
            }
        }    
         
        
        //LOG.debug("appointment.getId() = {}",newStud.id);
        LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
        //LOG.debug("Retrieving the Appointment Resource {}", newGrade);
        String xmlString = Converter.convertFromObjectToXml(studentlist.get(pos), Student.class);
        response = Response.status(Response.Status.OK).entity(xmlString).build();
            
               
        LOG.debug("Returning the value {}", response);
        return response;
    }
        
    //Getting the studwnt ID and updating information for that student
    @PUT
    @Path("{StudId}/{GradeItem}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateResource(@PathParam("StudId") String StudId,@PathParam("GradeItem") String GradeItem, String content){
        Response response = null;
        Student newStud=null;
        Grade newGrade=null;
    
        try {
            LOG.debug("GET request");
            LOG.debug("PathParam id = {}", StudId);
            LOG.debug("PathParam grade item = {}", GradeItem);
            newGrade = (Grade) Converter.convertFromXmlToObject(content, Grade.class);
            boolean flag = true;
            for(int i=0; i<studentlist.size() ;i++)
            {
                Student s3 = studentlist.get(i);
                if(s3.getId() == Integer.parseInt(StudId))
                {
                    LOG.debug("our     "+i);
                    for(int j = 0; j < s3.marks.size();j++)
                    {
                        LOG.debug("Yo     "+j);
                        if(s3.marks.get(j).gradeitem.equals(GradeItem))
                        {
                            LOG.debug("Inside");
                            s3.marks.set(j, newGrade);
                            flag = false;
                        }
                    }
                }
            }
            
            if (flag){
                LOG.info("Creating a {} {} Status Response", Response.Status.NOT_FOUND.getStatusCode(), Response.Status.GONE.getReasonPhrase());
                LOG.debug("No Grade Resource to return");
                
                response = Response.status(Response.Status.NOT_FOUND).entity("No Appointment Resource to return").build();
            } else {
                //LOG.debug("appointment.getId() = {}",newStud.id);
                LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
                LOG.debug("Retrieving the Appointment Resource {}", newGrade);
                String xmlString = Converter.convertFromObjectToXml(newGrade, Grade.class);
                response = Response.status(Response.Status.OK).entity(xmlString).build();
                
            }
            }
            catch (JAXBException ex) 
            {
                java.util.logging.Logger.getLogger(GradeResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            LOG.debug("Returning the value {}", response);
            return response;
    }
    
    
    //Taking the student ID and the grade item and deleting only that
    @DELETE
    @Path("{StudId}/{GradeItem}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteResource(@PathParam("StudId") String StudId,@PathParam("GradeItem") String GradeItem, String content){
        Response response = null;
        Student newStud=null;
        Grade newGrade=null;
    
        LOG.debug("GET request");
        LOG.debug("PathParam id = {}", StudId);
        LOG.debug("PathParam grade item = {}", GradeItem);
        boolean flag = true;
        for(int i=0; i<studentlist.size() ;i++)
        {
            Student s3 = studentlist.get(i);
            if(s3.getId() == Integer.parseInt(StudId))
            {
                LOG.debug("our     "+i);
                for(int j = 0; j < s3.marks.size();j++)
                {
                    LOG.debug("Yo     "+j);
                    if(s3.marks.get(j).gradeitem.equals(GradeItem))
                    {
                        LOG.debug("Inside delete");
                        newGrade = s3.marks.get(j);
                        s3.marks.remove(j);
                        flag = false;
                    }
                }
            }
        }
        if (flag){
            LOG.info("Creating a {} {} Status Response", Response.Status.NOT_FOUND.getStatusCode(), Response.Status.GONE.getReasonPhrase());
            LOG.debug("No Grade Resource to delete");
            
            response = Response.status(Response.Status.NOT_FOUND).entity("No grade Resource to return").build();
        } else {
            //LOG.debug("appointment.getId() = {}",newStud.id);
            LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
            LOG.debug("Retrieving the grade Resource {}", newGrade);
            String xmlString = Converter.convertFromObjectToXml(newGrade, Grade.class);
            response = Response.status(Response.Status.OK).entity(xmlString).build();
            
        }
            LOG.debug("Returning the value {}", response);
            return response;
    }
    
    //Taking the student ID and deleting all the grades
    @DELETE
    @Path("{StudId}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteAllResource(@PathParam("StudId") String StudId){
            Response response = null;
        
        try
        {
            LOG.debug("Calling Delete All");
        Student newStud=null;
        Grade newGrade=null;
    
        LOG.debug("GET request");
        LOG.debug("PathParam id = {}", StudId);
        LOG.debug("PathParam grade item = {}", StudId);
        boolean flag = true;
        int pos=0;
        for(int i=0; i<studentlist.size() ;i++)
        {
            Student s3 = studentlist.get(i);
            pos=i;
            if(s3.getId() == Integer.parseInt(StudId))
            {
                LOG.debug("our     "+i);
                   LOG.debug("Inside delete all");
                    //newGrade = s3.marks.get(j);
                    s3.marks.clear();
                    //flag = false;
            }
        }
        
            //LOG.debug("appointment.getId() = {}",newStud.id);
            LOG.info("Creating a {} {} Status Response", Response.Status.OK.getStatusCode(), Response.Status.OK.getReasonPhrase());
            LOG.debug("Retrieving the grade Resource {}", newGrade);
            String xmlString = Converter.convertFromObjectToXml(studentlist.get(pos), Student.class);
            response = Response.status(Response.Status.OK).entity(xmlString).build();
            
           
            LOG.debug("Returning the value {}", response);
        
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
                
            return response;
    }       
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GradeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
