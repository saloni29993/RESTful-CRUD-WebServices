/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.crud.restcl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saloni
 */

//This class consists of the marks and comments forming grade 
//for a student.
@XmlRootElement
public class Grade {

    public int getId() {
        return Id;
    }
    
    @XmlElement
    public void setId(int Id) {
        this.Id = Id;
    }
    
    int Id;
    String gradeitem;
    int weightage;
    int marks;
    String comments;

    public String getGradeitem() {
        return gradeitem;
    }

    @XmlElement
    public void setGradeitem(String gradeitem) {
        this.gradeitem = gradeitem;
    }

    public float getWeightage() {
        return weightage;
    }

    @XmlElement
    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }

    public int getMarks() {
        return marks;
    }

    @XmlElement
    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getComments() {
        return comments;
    }

    @XmlElement
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
    
}
