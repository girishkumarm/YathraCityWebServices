package com.yathraCity.cassandra.pojo;


public class CarDetailsPojo 
{
	private String carName;
    private String carModel;
    private String carNumber;
    private Integer maximumPeople;
    private String driverName;
    private long phoneNumber;
    private String cityName;
    private Integer minimumDistance;
    private Integer pricePerKm;
    private String driverLicence;
	
	public String getCarName()
	{
		return carName;
	}
	
	public void setCarName( String carName )
	{
		this.carName = carName;
	}
	
	public String getCarModel()
	{
		return carModel;
	}
	
	public void setCarModel( String carModel )
	{
		this.carModel = carModel;
	}
	
	public String getCarNumber()
	{
		return carNumber;
	}
	
	public void setCarNumber( String carNumber )
	{
		this.carNumber = carNumber;
	}
	
	public Integer getMaximumPeople()
	{
		return maximumPeople;
	}
	
	public void setMaximumPeople( Integer maximumPeople )
	{
		this.maximumPeople = maximumPeople;
	}
	
	public String getDriverName()
	{
		return driverName;
	}
	
	public void setDriverName( String driverName )
	{
		this.driverName = driverName;
	}
	
	public long getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber( long phoneNumber )
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getCityName()
	{
		return cityName;
	}
	
	public void setCityName( String cityName )
	{
		this.cityName = cityName;
	}
	
	public Integer getMinimumDistance()
	{
		return minimumDistance;
	}
	
	public void setMinimumDistance( Integer minimumDistance )
	{
		this.minimumDistance = minimumDistance;
	}
	
	public Integer getPricePerKm()
	{
		return pricePerKm;
	}
	
	public void setPricePerKm( Integer pricePerKm )
	{
		this.pricePerKm = pricePerKm;
	}
	
	public String getDriverLicence()
	{
		return driverLicence;
	}
	
	public void setDriverLicence( String driverLicence )
	{
		this.driverLicence = driverLicence;
	}

}
