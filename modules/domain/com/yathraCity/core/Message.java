package com.yathraCity.core;
// Generated 14 Jan, 2016 8:52:43 AM by Hibernate Tools 3.2.0.CR1



/**
 * Message generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class Message  implements java.io.Serializable {


     private Integer id;
     private String message;

    public Message() {
    }

    public Message(String message) {
       this.message = message;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }




}
