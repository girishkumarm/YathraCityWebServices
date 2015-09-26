package com.yathraCity.core;
// Generated 25 Sep, 2015 10:02:33 AM by Hibernate Tools 3.2.0.CR1



/**
 * AvailableCars generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class AvailableCars  implements java.io.Serializable {


     private Integer id;
     private String carName;
     private boolean carAvailability;
     private String carNumber;
     private String carOwner;
     private Integer carCapacity;
     private String carRegisteredAt;
     private Integer pricePerKilometer;
     private String ownerLicenseNumber;
     private String minimunDistancePerDay;
     private String contactNumber;

    public AvailableCars() {
    }

    public AvailableCars(String carName, boolean carAvailability, String carNumber, String carOwner, Integer carCapacity, String carRegisteredAt, Integer pricePerKilometer, String ownerLicenseNumber, String minimunDistancePerDay, String contactNumber) {
       this.carName = carName;
       this.carAvailability = carAvailability;
       this.carNumber = carNumber;
       this.carOwner = carOwner;
       this.carCapacity = carCapacity;
       this.carRegisteredAt = carRegisteredAt;
       this.pricePerKilometer = pricePerKilometer;
       this.ownerLicenseNumber = ownerLicenseNumber;
       this.minimunDistancePerDay = minimunDistancePerDay;
       this.contactNumber = contactNumber;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCarName() {
        return this.carName;
    }
    
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public boolean isCarAvailability() {
        return this.carAvailability;
    }
    
    public void setCarAvailability(boolean carAvailability) {
        this.carAvailability = carAvailability;
    }
    public String getCarNumber() {
        return this.carNumber;
    }
    
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public String getCarOwner() {
        return this.carOwner;
    }
    
    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }
    public Integer getCarCapacity() {
        return this.carCapacity;
    }
    
    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }
    public String getCarRegisteredAt() {
        return this.carRegisteredAt;
    }
    
    public void setCarRegisteredAt(String carRegisteredAt) {
        this.carRegisteredAt = carRegisteredAt;
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
    public String getContactNumber() {
        return this.contactNumber;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }




}
