package com.yathraCity.core;
// Generated 4 Oct, 2015 1:21:43 PM by Hibernate Tools 3.2.0.CR1



/**
 * ListOfAvailableCars generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class ListOfAvailableCars  implements java.io.Serializable {


     private Integer id;

    public ListOfAvailableCars() {
    }

   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }



	private List<AvailableCars> car = new ArrayList<AvailableCars>();

	@XmlElement
	public List<AvailableCars> getCar(){return car;}
	public void setcar (List<AvailableCars> arr) {this.car=arr;}


}
