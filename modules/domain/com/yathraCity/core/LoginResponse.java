package com.yathraCity.core;
// Generated 7 Jan, 2016 11:13:43 PM by Hibernate Tools 3.2.0.CR1



/**
 * LoginResponse generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class LoginResponse  implements java.io.Serializable {


     private Integer id;
     private String flag;
     private String message;

    public LoginResponse() {
    }

    public LoginResponse(String flag, String message) {
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
