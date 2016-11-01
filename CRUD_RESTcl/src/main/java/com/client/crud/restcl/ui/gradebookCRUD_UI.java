/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.crud.restcl.ui;

import com.client.crud.restcl.Grade_CRUD_cl;
import com.client.crud.restcl.Grade;
import com.client.crud.restcl.Gradebook;
import com.client.crud.restcl.Student;
import com.client.crud.restcl.Converter;
import com.client.crud.restcl.clientService;
import com.sun.jersey.api.client.ClientResponse;
import java.awt.CardLayout;
import java.net.URI;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class gradebookCRUD_UI extends javax.swing.JFrame {

    /**
     * Creates new form gradebookCRUD_UI
     */
    private static final Logger LOG = LoggerFactory.getLogger(gradebookCRUD_UI.class);
    
    private Grade_CRUD_cl grade_CRUD_rest_client;
    
    private URI resourceURI;
    
    
    public gradebookCRUD_UI() {
        LOG.info("Creating a Appointment_REST_UI object");
        initComponents();
        
        grade_CRUD_rest_client = new Grade_CRUD_cl();
    }
    
    //for create
    private String convertFormToXMLStringCreate(){
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(jTextField1.getText()));
        grade.setGradeitem(jTextField2.getText());
        grade.setWeightage(Integer.parseInt(jTextField3.getText()));
        grade.setMarks(Integer.parseInt(jTextField4.getText()));
        grade.setComments(jTextField5.getText());
            
        String xmlString = Converter.convertFromObjectToXml(grade, grade.getClass());
        
        return xmlString;
    }
    
    //for read
    private String convertFormToXMLStringRead(){
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(jTextField25.getText()));
        grade.setGradeitem(jTextField26.getText());
        //grade.setWeightage(jTextField27.getText());
        //grade.setMarks(Integer.parseInt(jTextField28.getText()));
        //grade.setComments(jTextField29.getText());
            
        String xmlString = Converter.convertFromObjectToXml(grade, grade.getClass());
        
        return xmlString;
    }
    
    //for read all
    private String convertFormToXMLStringReadAll(){
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(jTextField25.getText()));
        //grade.setGradeitem(jTextField26.getText());
        //grade.setWeightage(jTextField27.getText());
        //grade.setMarks(Integer.parseInt(jTextField28.getText()));
        //grade.setComments(jTextField29.getText());
            
        String xmlString = Converter.convertFromObjectToXml(grade, grade.getClass());
        
        return xmlString;
    }
    
    //for update
    private String convertFormToXMLStringUpdate(){
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(jTextField17.getText()));
        grade.setGradeitem(jTextField18.getText());
        grade.setWeightage(Integer.parseInt(jTextField19.getText()));
        grade.setMarks(Integer.parseInt(jTextField20.getText()));
        grade.setComments(jTextField21.getText());
            
        String xmlString = Converter.convertFromObjectToXml(grade, grade.getClass());
        
        return xmlString;
    }
    
    //for delete
    private String convertFormToXMLStringDelete(){
        Grade grade = new Grade();
        grade.setId(Integer.parseInt(jTextField33.getText()));
        grade.setGradeitem(jTextField34.getText());
        //grade.setMarks(Integer.parseInt(jTextField22.getText()));
        //grade.setComments(jTextField23.getText());
            
        String xmlString = Converter.convertFromObjectToXml(grade, grade.getClass());
        
        return xmlString;
    }

    
    //populate for create
    private void populateFormCreate(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Grade info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                Grade grade = (Grade)Converter.convertFromXmlToObject(entity, Grade.class);
                LOG.debug("The Client Response appointment object is {}", grade);
                
                // Populate Appointment info
                jTextField1.setText(Integer.toString(grade.getId()));
                jTextField2.setText(grade.getGradeitem());
                jTextField3.setText(Integer.toString((int)grade.getWeightage()));
                jTextField4.setText(Integer.toString(grade.getMarks()));
                jTextField5.setText(grade.getComments());
            } else {
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
            }
            
            // Populate HTTP Header Information
            jTextField6.setText(Integer.toString(clientResponse.getStatus()));
            jTextField7.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                jTextField8.setText(clientResponse.getLocation().toString());
            } else {
                jTextField8.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    } 
    
    //populate for read
    private void populateFormRead(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Grade info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                Grade grade = (Grade)Converter.convertFromXmlToObject(entity, Grade.class);
                LOG.debug("The Client Response appointment object is {}", grade);
                
                // Populate Appointment info
                jTextField25.setText(Integer.toString(grade.getId()));
                jTextField26.setText(grade.getGradeitem());
                jTextField27.setText(Integer.toString((int) grade.getWeightage()));
                jTextField28.setText(Integer.toString(grade.getMarks()));
                jTextField29.setText(grade.getComments());
            } else {
                jTextField27.setText("");
                jTextField28.setText("Record Not Found");
                jTextField29.setText("");
            }
            
            // Populate HTTP Header Information
            jTextField30.setText(Integer.toString(clientResponse.getStatus()));
            jTextField31.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode() || 
                    clientResponse.getStatus() == Response.Status.OK.getStatusCode()){
                
                //LOG.debug("read all location uri test: {}" ,clientResponse.getLocation().toString());
//                jTextField32.setText(clientResponse.getLocation().toString());
            } else {
                //LOG.debug("else part location uri test");
                jTextField32.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }  
    
    //populate for read all
    private void populateFormReadAll(ClientResponse clientResponse){
        LOG.info("Populating the UI with the All Grades info");
        //LOG.info("Response for populate all grade :" +);
        String entity = clientResponse.getEntity(String.class);
        //int stuid = Integer.parseInt(jTextField12.getText());
        LOG.debug("The Client Response entity is {}", entity);
        List<Grade> gradelist = new ArrayList<Grade>();
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                gradelist= (ArrayList)Converter.convertFromXmlToObject(entity, Grade.class);
                //LOG.debug("The Client Response appointment object is {}", grade);
                
                String newgradestring = "";
                // Populate Appointment info
                
                        for(int j=0;j<gradelist.size();j++)
                        {
                            
                             newgradestring += gradelist.get(j).getGradeitem() +"\t" +gradelist.get(j).getMarks() +"\n";
                        }
                  
                
                LOG.debug("new grade string: " +newgradestring);
                //jTextField12.setText(Integer.toString(grade.getId()));
                //jTextArea1.setText(grade.getGradeitem());
                //jTextField14.setText(Integer.toString(grade.getMarks()));
                //jTextField15.setText(grade.getComments());
                jTextArea1.setText(newgradestring);
            } else {
                jTextField27.setText("");
                jTextField28.setText("Record Not Found");
                jTextField29.setText("");
            }
            
            // Populate HTTP Header Information
            jTextField30.setText(Integer.toString(clientResponse.getStatus()));
            jTextField31.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                jTextField32.setText(clientResponse.getLocation().toString());
            } else {
                jTextField32.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    } 
    
    
    //popultae for update
    private void populateFormUpdate(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Grade info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                Grade grade = (Grade)Converter.convertFromXmlToObject(entity, Grade.class);
                LOG.debug("The Client Response appointment object is {}", grade);
                
                // Populate Appointment info
                jTextField17.setText(Integer.toString(grade.getId()));
                jTextField18.setText(grade.getGradeitem());
                jTextField19.setText(Integer.toString((int) grade.getWeightage()));
                jTextField20.setText(Integer.toString(grade.getMarks()));
                jTextField21.setText(grade.getComments());
            } else {
                jTextField19.setText("");
                jTextField20.setText("Record Not Found");
                jTextField21.setText("");
            }
            
            // Populate HTTP Header Information
            jTextField22.setText(Integer.toString(clientResponse.getStatus()));
            jTextField23.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                jTextField24.setText(clientResponse.getLocation().toString());
            } else {
                jTextField24.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }


private void populateFormDelete(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Grade info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        try{
            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                Grade grade = (Grade)Converter.convertFromXmlToObject(entity, Grade.class);
                LOG.debug("The Client Response appointment object is {}", grade);
                
                // Populate Appointment info
                jTextField33.setText(Integer.toString(grade.getId()));
                jTextField34.setText(grade.getGradeitem());
                } else {
                jTextField33.setText("");
                jTextField34.setText("");
            }
            
            // Populate HTTP Header Information
            jTextField38.setText(Integer.toString(clientResponse.getStatus()));
            jTextField39.setText(clientResponse.getType().toString());
            
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                jTextField40.setText(clientResponse.getLocation().toString());
            } else {
                jTextField40.setText("");
            }
            
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }

//for delete all
private void populateFormDeleteAll(ClientResponse clientResponse){
        LOG.info("Populating the UI with the Grade info");
        
        String entity = clientResponse.getEntity(String.class);
        
        LOG.debug("The Client Response entity is {}", entity);
        
        if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
            // Student grade = (Student)Converter.convertFromXmlToObject(entity, Student.class);
            // LOG.debug("The Client Response appointment object is {}", grade);
            
            // Populate Appointment info
            // jTextField27.setText(Integer.toString(grade.getId()));
            //jTextField28.setText(grade.getGradeitem());
        } else {
            jTextField33.setText("");
            jTextField34.setText("");
        }
        LOG.debug(Integer.toString(clientResponse.getStatus())+"testingField31DeleteAllCaptain");
        jTextField38.setText(Integer.toString(clientResponse.getStatus()));
        jTextField39.setText(clientResponse.getType().toString());
        if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
            jTextField40.setText(clientResponse.getLocation().toString());
        } else {
            jTextField40.setText("");
        }
    }
            
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InstPanel = new javax.swing.JPanel();
        ForCreate = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        ForUpdate = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        ForRead = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        ForDelete = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InstPanel.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Create student records here");

        jLabel3.setText("Student ID");

        jLabel4.setText("Grade for Item");

        jLabel5.setText("Weightage");

        jLabel6.setText("Marks");

        jLabel7.setText("Comments");

        jButton1.setText("Save Grades");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Status");

        jLabel9.setText("Media Type");

        jLabel10.setText("Location");

        jTextField8.setText("jTextField8");

        javax.swing.GroupLayout ForCreateLayout = new javax.swing.GroupLayout(ForCreate);
        ForCreate.setLayout(ForCreateLayout);
        ForCreateLayout.setHorizontalGroup(
            ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForCreateLayout.createSequentialGroup()
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ForCreateLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jButton1))
                    .addGroup(ForCreateLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addGap(35, 35, 35)
                        .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8)))
                    .addGroup(ForCreateLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        ForCreateLayout.setVerticalGroup(
            ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForCreateLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(15, 15, 15)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(239, Short.MAX_VALUE))
        );

        InstPanel.add(ForCreate, "cardCreate");

        jLabel12.setText("Update Student records here");

        jLabel21.setText("Student ID");

        jLabel22.setText("Grade for Item");

        jLabel23.setText("Weightage");

        jLabel24.setText("Marks");

        jLabel25.setText("Comments");

        jButton7.setText("Save Updated Grades");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel26.setText("Status");

        jLabel27.setText("Media Type");

        jLabel28.setText("Location");

        javax.swing.GroupLayout ForUpdateLayout = new javax.swing.GroupLayout(ForUpdate);
        ForUpdate.setLayout(ForUpdateLayout);
        ForUpdateLayout.setHorizontalGroup(
            ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForUpdateLayout.createSequentialGroup()
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ForUpdateLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel28))
                        .addGap(35, 35, 35)
                        .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jTextField18)
                            .addComponent(jTextField19)
                            .addComponent(jTextField20)
                            .addComponent(jTextField21)
                            .addComponent(jTextField22)
                            .addComponent(jTextField23)
                            .addComponent(jTextField24)))
                    .addGroup(ForUpdateLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ForUpdateLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        ForUpdateLayout.setVerticalGroup(
            ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForUpdateLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jButton7)
                .addGap(38, 38, 38)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        InstPanel.add(ForUpdate, "cardUpdate");

        jLabel13.setText("View Student records here");

        jLabel29.setText("Student ID");

        jLabel30.setText("Grade for Item");

        jLabel31.setText("Weightage");

        jLabel32.setText("Marks");

        jLabel33.setText("Comments");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        jButton9.setText("View Grades");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel34.setText("Status");

        jLabel35.setText("Media Type");

        jLabel36.setText("Location");

        jButton10.setText("View All Grades");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel39.setText("Grade for all items");

        javax.swing.GroupLayout ForReadLayout = new javax.swing.GroupLayout(ForRead);
        ForRead.setLayout(ForReadLayout);
        ForReadLayout.setHorizontalGroup(
            ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForReadLayout.createSequentialGroup()
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ForReadLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ForReadLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jButton9)
                        .addGap(79, 79, 79)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ForReadLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel36))
                        .addGap(35, 35, 35)
                        .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addComponent(jTextField26)
                                .addComponent(jTextField27)
                                .addComponent(jTextField28)
                                .addComponent(jTextField29)
                                .addComponent(jTextField30)
                                .addComponent(jTextArea1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        ForReadLayout.setVerticalGroup(
            ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForReadLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForReadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167))
        );

        InstPanel.add(ForRead, "cardRead");

        jLabel14.setText("Delete Student records here");

        jLabel37.setText("Student ID");

        jLabel38.setText("Grade for Item");

        jButton8.setText("Delete Grade Item");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel42.setText("Status");

        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });

        jLabel43.setText("Media Type");

        jTextField39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField39ActionPerformed(evt);
            }
        });

        jLabel44.setText("Location");

        jButton2.setText("Delete All Grade Items");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ForDeleteLayout = new javax.swing.GroupLayout(ForDelete);
        ForDelete.setLayout(ForDeleteLayout);
        ForDeleteLayout.setHorizontalGroup(
            ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForDeleteLayout.createSequentialGroup()
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ForDeleteLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ForDeleteLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addComponent(jLabel44))
                        .addGap(35, 35, 35)
                        .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField33, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addComponent(jTextField34)
                                .addComponent(jTextField38)
                                .addComponent(jTextField39)
                                .addComponent(jTextField40))
                            .addGroup(ForDeleteLayout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jButton2)))))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        ForDeleteLayout.setVerticalGroup(
            ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ForDeleteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton2))
                .addGap(32, 32, 32)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ForDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(331, Short.MAX_VALUE))
        );

        InstPanel.add(ForDelete, "cardDelete");

        jLabel1.setText("WELCOME INSTRUCTOR");

        jButton3.setText("CREATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("READ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("UPDATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("DELETE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(33, 33, 33)
                .addComponent(jButton5)
                .addGap(31, 31, 31)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField1.getText();
        
        LOG.debug("Invoking {} action", jButton1.getText());//Create
            
        ClientResponse clientResponse = grade_CRUD_rest_client.createGrade(this.convertFormToXMLStringCreate());
            
        resourceURI = clientResponse.getLocation();
        LOG.debug("Retrieved location {}", resourceURI);
            
        this.populateFormCreate(clientResponse);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField17.getText();        
        String GraName = jTextField18.getText();
        LOG.debug("Invoking {} action", jButton7.getText());//Update
            
        ClientResponse clientResponse = grade_CRUD_rest_client.updateGrade(this.convertFormToXMLStringUpdate(), gradeID, GraName);
 
        this.populateFormUpdate(clientResponse);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)InstPanel.getLayout();
        card.show(InstPanel,"cardCreate");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)InstPanel.getLayout();
        card.show(InstPanel,"cardRead");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)InstPanel.getLayout();
        card.show(InstPanel,"cardUpdate");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)InstPanel.getLayout();
        card.show(InstPanel,"cardDelete");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField25.getText();
        String gradeItem = jTextField26.getText();
        
        LOG.debug("Invoking {} action", jButton9.getText());
                    
        ClientResponse clientResponse = grade_CRUD_rest_client.retrieveGrade(ClientResponse.class, gradeID, gradeItem);
        LOG.debug("response of read {}",clientResponse.toString());    
        this.populateFormRead(clientResponse);  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField33.getText();
        String GraName = jTextField34.getText();
        
        LOG.debug("Invoking {} action", jButton8.getText());//Delete
            
        ClientResponse clientResponse = grade_CRUD_rest_client.deleteGrade(gradeID,GraName);
        this.populateFormDelete(clientResponse);  
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField25.getText();
        //String gradeItem = jTextField13.getText();
        
        LOG.debug("Invoking {} action", jButton10.getText());
                    
        ClientResponse clientResponse = grade_CRUD_rest_client.retrieveAllGrade(ClientResponse.class, gradeID);
        LOG.debug("response of read {}",clientResponse.toString());    
        this.populateFormReadAll(clientResponse);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        LOG.info("Invoking REST Client based on selection");
        
        String gradeID = jTextField33.getText();
        //String GraName = jTextField34.getText();
        
        LOG.debug("Invoking {} action", jButton2.getText());//Delete all
            
        ClientResponse clientResponse = grade_CRUD_rest_client.deleteAllGrade(gradeID);
        this.populateFormDeleteAll(clientResponse);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gradebookCRUD_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gradebookCRUD_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gradebookCRUD_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gradebookCRUD_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gradebookCRUD_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ForCreate;
    private javax.swing.JPanel ForDelete;
    private javax.swing.JPanel ForRead;
    private javax.swing.JPanel ForUpdate;
    private javax.swing.JPanel InstPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
