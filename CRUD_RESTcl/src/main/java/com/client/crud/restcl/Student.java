/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.crud.restcl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saloni
 */

@XmlRootElement
//Class will consist of Student ID and Grade
@XmlAccessorType (XmlAccessType.FIELD)
public class Student {
 public int id;
 @XmlElement (name = "Grade", type = Grade.class)
 public List<Grade> marks=new ArrayList<Grade>();

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }
}

