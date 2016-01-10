package com.yathraCity.cassandra.pojo;


public class DriverDetailsPojo {

    private String driverPhoneNumber;
    private String driverLicence;
    private String driverName;
    private String carId;
    private String agencyName;
    private String agencyPhoneNumber;
    private String location;
	
	public String getDriverPhoneNumber()
	{
		return driverPhoneNumber;
	}
	
	public void setDriverPhoneNumber( String driverPhoneNumber )
	{
		this.driverPhoneNumber = driverPhoneNumber;
	}
	
	public String getDriverLicence()
	{
		return driverLicence;
	}
	
	public void setDriverLicence( String driverLicence )
	{
		this.driverLicence = driverLicence;
	}
	
	public String getDriverName()
	{
		return driverName;
	}
	
	public void setDriverName( String driverName )
	{
		this.driverName = driverName;
	}
	
	public String getCarId()
	{
		return carId;
	}
	
	public void setCarId( String carId )
	{
		this.carId = carId;
	}
	
	public String getAgencyName()
	{
		return agencyName;
	}
	
	public void setAgencyName( String agencyName )
	{
		this.agencyName = agencyName;
	}
	
	public String getAgencyPhoneNumber()
	{
		return agencyPhoneNumber;
	}
	
	public void setAgencyPhoneNumber( String agencyPhoneNumber )
	{
		this.agencyPhoneNumber = agencyPhoneNumber;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation( String location )
	{
		this.location = location;
	}

}
