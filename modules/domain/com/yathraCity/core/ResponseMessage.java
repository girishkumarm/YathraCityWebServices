package com.yathraCity.core;
// Generated 14 Jan, 2016 9:50:36 AM by Hibernate Tools 3.2.0.CR1



/**
 * ResponseMessage generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class ResponseMessage  implements java.io.Serializable {


     private Integer id;
     private String status;
     private String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String status, String message) {
       this.status = status;
       this.message = message;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }




}
