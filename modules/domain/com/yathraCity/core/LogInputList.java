package com.yathraCity.core;
// Generated 8 Jan, 2016 6:15:46 PM by Hibernate Tools 3.2.0.CR1



/**
 * LogInputList generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class LogInputList  implements java.io.Serializable {


     private Integer id;

    public LogInputList() {
    }

   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }



	private List<LogInput> logInput = new ArrayList<LogInput>();

	@XmlElement
	public List<LogInput> getLogInput(){return logInput;}
	public void setlogInput (List<LogInput> arr) {this.logInput=arr;}


}
