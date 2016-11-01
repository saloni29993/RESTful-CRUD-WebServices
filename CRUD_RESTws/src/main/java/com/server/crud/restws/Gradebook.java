/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.crud.restws;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saloni
 */

//This class will consist of the details of many students.
public class Gradebook {
  public List<Student> gradebook = new ArrayList<Student>();
  
  private static final Logger LOG = LoggerFactory.getLogger(GradeResource.class);
  public Gradebook()
  {
      LOG.debug("Called Gradebook class... Adding student");
  }
  
}