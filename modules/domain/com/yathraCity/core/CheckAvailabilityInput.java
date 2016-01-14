package com.yathraCity.core;
// Generated 14 Jan, 2016 9:50:36 AM by Hibernate Tools 3.2.0.CR1



/**
 * CheckAvailabilityInput generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class CheckAvailabilityInput  implements java.io.Serializable {


     private Integer id;
     private String carNumber;
     private String carRegisteredAt;
     private Integer carCapacity;
     private Integer pricePerKilometer;
     private String ownerLicenseNumber;
     private String minimunDistancePerDay;

    public CheckAvailabilityInput() {
    }

    public CheckAvailabilityInput(String carNumber, String carRegisteredAt, Integer carCapacity, Integer pricePerKilometer, String ownerLicenseNumber, String minimunDistancePerDay) {
       this.carNumber = carNumber;
       this.carRegisteredAt = carRegisteredAt;
       this.carCapacity = carCapacity;
       this.pricePerKilometer = pricePerKilometer;
       this.ownerLicenseNumber = ownerLicenseNumber;
       this.minimunDistancePerDay = minimunDistancePerDay;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCarNumber() {
        return this.carNumber;
    }
    
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getCarRegisteredAt() {
        return this.carRegisteredAt;
    }
    
    public void setCarRegisteredAt(String carRegisteredAt) {
        this.carRegisteredAt = carRegisteredAt;
    }
    public Integer getCarCapacity() {
        return this.carCapacity;
    }
    
    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }
    public Integer getPricePerKilometer() {
        return this.pricePerKilometer;
    }
    
    public void setPricePerKilometer(Integer pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }
    public String getOwnerLicenseNumber() {
        return this.ownerLicenseNumber;
    }
    
    public void setOwnerLicenseNumber(String ownerLicenseNumber) {
        this.ownerLicenseNumber = ownerLicenseNumber;
    }
    public String getMinimunDistancePerDay() {
        return this.minimunDistancePerDay;
    }
    
    public void setMinimunDistancePerDay(String minimunDistancePerDay) {
        this.minimunDistancePerDay = minimunDistancePerDay;
    }




}
