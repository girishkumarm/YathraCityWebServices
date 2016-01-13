package com.yathraCity.core;
// Generated 12 Jan, 2016 8:54:37 PM by Hibernate Tools 3.2.0.CR1



/**
 * DriverDetails generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class DriverDetails  implements java.io.Serializable {


     private Integer id;
     private String driverName;
     private String driverPhoneNumber;
     private String driverLicence;
     private String agencyName;
     private String agencyPhoneNumber;
     private String location;
     private String carType;
     private String carNumber;

    public DriverDetails() {
    }

    public DriverDetails(String driverName, String driverPhoneNumber, String driverLicence, String agencyName, String agencyPhoneNumber, String location, String carType, String carNumber) {
       this.driverName = driverName;
       this.driverPhoneNumber = driverPhoneNumber;
       this.driverLicence = driverLicence;
       this.agencyName = agencyName;
       this.agencyPhoneNumber = agencyPhoneNumber;
       this.location = location;
       this.carType = carType;
       this.carNumber = carNumber;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDriverName() {
        return this.driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getDriverPhoneNumber() {
        return this.driverPhoneNumber;
    }
    
    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }
    public String getDriverLicence() {
        return this.driverLicence;
    }
    
    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }
    public String getAgencyName() {
        return this.agencyName;
    }
    
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getAgencyPhoneNumber() {
        return this.agencyPhoneNumber;
    }
    
    public void setAgencyPhoneNumber(String agencyPhoneNumber) {
        this.agencyPhoneNumber = agencyPhoneNumber;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCarType() {
        return this.carType;
    }
    
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarNumber() {
        return this.carNumber;
    }
    
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }




}
