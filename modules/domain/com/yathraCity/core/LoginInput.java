package com.yathraCity.core;
// Generated 8 Jan, 2016 6:15:46 PM by Hibernate Tools 3.2.0.CR1



/**
 * LoginInput generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class LoginInput  implements java.io.Serializable {


     private Integer id;
     private String yathraCityUserId;
     private String userAccountId;
     private String password;

    public LoginInput() {
    }

    public LoginInput(String yathraCityUserId, String userAccountId, String password) {
       this.yathraCityUserId = yathraCityUserId;
       this.userAccountId = userAccountId;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getYathraCityUserId() {
        return this.yathraCityUserId;
    }
    
    public void setYathraCityUserId(String yathraCityUserId) {
        this.yathraCityUserId = yathraCityUserId;
    }
    public String getUserAccountId() {
        return this.userAccountId;
    }
    
    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}
