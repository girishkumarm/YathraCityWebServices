package com.yathraCity.core;
// Generated 14 Jan, 2016 9:50:36 AM by Hibernate Tools 3.2.0.CR1



/**
 * LogInput generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class LogInput  implements java.io.Serializable {


     private Integer id;
     private String inbloxUserId;
     private String userAccountId;
     private String action;
     private String status;
     private String message;
     private String startTime;
     private String endTime;

    public LogInput() {
    }

    public LogInput(String inbloxUserId, String userAccountId, String action, String status, String message, String startTime, String endTime) {
       this.inbloxUserId = inbloxUserId;
       this.userAccountId = userAccountId;
       this.action = action;
       this.status = status;
       this.message = message;
       this.startTime = startTime;
       this.endTime = endTime;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getInbloxUserId() {
        return this.inbloxUserId;
    }
    
    public void setInbloxUserId(String inbloxUserId) {
        this.inbloxUserId = inbloxUserId;
    }
    public String getUserAccountId() {
        return this.userAccountId;
    }
    
    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }
    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = action;
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
    public String getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



	private List<RequestObject> requestObject = new ArrayList<RequestObject>();

	@XmlElement
	public List<RequestObject> getRequestObject(){return requestObject;}
	public void setrequestObject (List<RequestObject> arr) {this.requestObject=arr;}


}
