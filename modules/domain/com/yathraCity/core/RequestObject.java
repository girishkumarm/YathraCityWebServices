package com.yathraCity.core;
// Generated 7 Jan, 2016 11:13:43 PM by Hibernate Tools 3.2.0.CR1



/**
 * RequestObject generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class RequestObject  implements java.io.Serializable {


     private Integer id;
     private String subject;
     private long messageUid;

    public RequestObject() {
    }

    public RequestObject(String subject, long messageUid) {
       this.subject = subject;
       this.messageUid = messageUid;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public long getMessageUid() {
        return this.messageUid;
    }
    
    public void setMessageUid(long messageUid) {
        this.messageUid = messageUid;
    }




}
