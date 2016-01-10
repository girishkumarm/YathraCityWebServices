package com.yathraCity.core;
// Generated 8 Jan, 2016 6:15:46 PM by Hibernate Tools 3.2.0.CR1



/**
 * RegisterBookingInput generated by hbm2java
 */
import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement
public class RegisterBookingInput  implements java.io.Serializable {


     private Integer id;
     private String userName;
     private String emailId;
     private long phoneNumber;
     private String numberOfPeoples;
     private String bookedCarName;
     private long carBookedAt;
     private Integer pickUpPoint;
     private String destination;
     private Integer numberOfDays;
     private String bookedCarNumber;
     private String bookedCarOwner;
     private Integer discountOffered;
     private boolean bookingConfirmation;

    public RegisterBookingInput() {
    }

    public RegisterBookingInput(String userName, String emailId, long phoneNumber, String numberOfPeoples, String bookedCarName, long carBookedAt, Integer pickUpPoint, String destination, Integer numberOfDays, String bookedCarNumber, String bookedCarOwner, Integer discountOffered, boolean bookingConfirmation) {
       this.userName = userName;
       this.emailId = emailId;
       this.phoneNumber = phoneNumber;
       this.numberOfPeoples = numberOfPeoples;
       this.bookedCarName = bookedCarName;
       this.carBookedAt = carBookedAt;
       this.pickUpPoint = pickUpPoint;
       this.destination = destination;
       this.numberOfDays = numberOfDays;
       this.bookedCarNumber = bookedCarNumber;
       this.bookedCarOwner = bookedCarOwner;
       this.discountOffered = discountOffered;
       this.bookingConfirmation = bookingConfirmation;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmailId() {
        return this.emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public long getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getNumberOfPeoples() {
        return this.numberOfPeoples;
    }
    
    public void setNumberOfPeoples(String numberOfPeoples) {
        this.numberOfPeoples = numberOfPeoples;
    }
    public String getBookedCarName() {
        return this.bookedCarName;
    }
    
    public void setBookedCarName(String bookedCarName) {
        this.bookedCarName = bookedCarName;
    }
    public long getCarBookedAt() {
        return this.carBookedAt;
    }
    
    public void setCarBookedAt(long carBookedAt) {
        this.carBookedAt = carBookedAt;
    }
    public Integer getPickUpPoint() {
        return this.pickUpPoint;
    }
    
    public void setPickUpPoint(Integer pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }
    public String getDestination() {
        return this.destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Integer getNumberOfDays() {
        return this.numberOfDays;
    }
    
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    public String getBookedCarNumber() {
        return this.bookedCarNumber;
    }
    
    public void setBookedCarNumber(String bookedCarNumber) {
        this.bookedCarNumber = bookedCarNumber;
    }
    public String getBookedCarOwner() {
        return this.bookedCarOwner;
    }
    
    public void setBookedCarOwner(String bookedCarOwner) {
        this.bookedCarOwner = bookedCarOwner;
    }
    public Integer getDiscountOffered() {
        return this.discountOffered;
    }
    
    public void setDiscountOffered(Integer discountOffered) {
        this.discountOffered = discountOffered;
    }
    public boolean isBookingConfirmation() {
        return this.bookingConfirmation;
    }
    
    public void setBookingConfirmation(boolean bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }




}
