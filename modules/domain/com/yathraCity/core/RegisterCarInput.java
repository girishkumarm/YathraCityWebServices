package com.yathraCity.core;
// Generated 10 Jan, 2016 2:31:31 PM by Hibernate Tools 3.2.0.CR1



/**
 * RegisterCarInput generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class RegisterCarInput  implements java.io.Serializable {


     private Integer id;
     private String carName;
     private boolean registered;
     private boolean carAvailability;
     private String carNumber;
     private Integer carCapacity;
     private String carRegisteredAt;
     private Integer pricePerKilometer;
     private Integer minimunDistancePerDay;
     private String carType;
     private String carModel;

    public RegisterCarInput() {
    }

    public RegisterCarInput(String carName, boolean registered, boolean carAvailability, String carNumber, Integer carCapacity, String carRegisteredAt, Integer pricePerKilometer, Integer minimunDistancePerDay, String carType, String carModel) {
       this.carName = carName;
       this.registered = registered;
       this.carAvailability = carAvailability;
       this.carNumber = carNumber;
       this.carCapacity = carCapacity;
       this.carRegisteredAt = carRegisteredAt;
       this.pricePerKilometer = pricePerKilometer;
       this.minimunDistancePerDay = minimunDistancePerDay;
       this.carType = carType;
       this.carModel = carModel;
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
    public boolean isRegistered() {
        return this.registered;
    }
    
    public void setRegistered(boolean registered) {
        this.registered = registered;
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
    public Integer getMinimunDistancePerDay() {
        return this.minimunDistancePerDay;
    }
    
    public void setMinimunDistancePerDay(Integer minimunDistancePerDay) {
        this.minimunDistancePerDay = minimunDistancePerDay;
    }
    public String getCarType() {
        return this.carType;
    }
    
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarModel() {
        return this.carModel;
    }
    
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }




}
