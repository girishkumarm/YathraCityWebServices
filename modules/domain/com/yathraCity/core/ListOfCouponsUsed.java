package com.yathraCity.core;
// Generated 14 Jan, 2016 8:52:43 AM by Hibernate Tools 3.2.0.CR1



/**
 * ListOfCouponsUsed generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class ListOfCouponsUsed  implements java.io.Serializable {


     private Integer id;

    public ListOfCouponsUsed() {
    }

   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }



	private List<CouponDetails> listOfCoupons = new ArrayList<CouponDetails>();

	@XmlElement
	public List<CouponDetails> getListOfCoupons(){return listOfCoupons;}
	public void setlistOfCoupons (List<CouponDetails> arr) {this.listOfCoupons=arr;}


}