package com.yathraCity.core;
// Generated 14 Jan, 2016 8:52:43 AM by Hibernate Tools 3.2.0.CR1



/**
 * RegisterUserResponse generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class RegisterUserResponse  implements java.io.Serializable {


     private Integer id;
     private String flag;
     private String message;

    public RegisterUserResponse() {
    }

    public RegisterUserResponse(String flag, String message) {
       this.flag = flag;
       this.message = message;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }




}
