package com.yathraCity.cassandra.pojo;

public class CarDetails {

	private String carName;
	private String carType;
	private boolean registered;
	private boolean carAvailability;
	private String carModel;
	private int carCapacity;
	private String carNumber;
	private String carRegisteredAt;
	private int pricePerKilometer;
	private int minimunDistancePerDay;

	public String getCarName()
	{
		return carName;
	}

	public void setCarName( String carName )
	{
		this.carName = carName;
	}

	public boolean isCarAvailability()
	{
		return carAvailability;
	}

	public void setCarAvailability( boolean carAvailability )
	{
		this.carAvailability = carAvailability;
	}

	public String getCarModel()
	{
		return carModel;
	}

	public void setCarModel( String carModel )
	{
		this.carModel = carModel;
	}

	public int getCarCapacity()
	{
		return carCapacity;
	}

	public void setCarCapacity( int carCapacity )
	{
		this.carCapacity = carCapacity;
	}

	public String getCarNumber()
	{
		return carNumber;
	}

	public void setCarNumber( String carNumber )
	{
		this.carNumber = carNumber;
	}

	public String getCarRegisteredAt()
	{
		return carRegisteredAt;
	}

	public void setCarRegisteredAt( String carRegisteredAt )
	{
		this.carRegisteredAt = carRegisteredAt;
	}

	public int getPricePerKilometer()
	{
		return pricePerKilometer;
	}

	public void setPricePerKilometer( int pricePerKilometer )
	{
		this.pricePerKilometer = pricePerKilometer;
	}

	public int getMinimunDistancePerDay()
	{
		return minimunDistancePerDay;
	}

	public void setMinimunDistancePerDay( int minimunDistancePerDay )
	{
		this.minimunDistancePerDay = minimunDistancePerDay;
	}

	public boolean isRegistered()
	{
		return registered;
	}

	public void setRegistered( boolean registered )
	{
		this.registered = registered;
	}

	public String getCarType()
	{
		return carType;
	}

	public void setCarType( String carType )
	{
		this.carType = carType;
	}

}
