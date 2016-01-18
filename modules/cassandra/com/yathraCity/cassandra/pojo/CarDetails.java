package com.yathraCity.cassandra.pojo;

public class CarDetails {

	private String bookingId;
	private String fromDate;
	private String toDate;
	private String carNumber;
	private String travelCity;
	private String carAgency;
	private String carAgencyPhoneNumber;
	private String driverName;
	private String drivePhoneNumber;
	private String coupon;
	private String customerName;
	private String customerNumber;
	private String pickUpCity;
	private String address;
	private String carLocation;
	private String carType;
	private int carCapacity;
	private String carModel;
	private String carRegisteredAt;
	private int minimunDistancePerDay;
	private int pricePerKilometer;
	private String carName;
	private boolean registered;
	private String licenseNumber;

	public boolean isRegistered()
	{
		return registered;
	}

	public void setRegistered( boolean registered )
	{
		this.registered = registered;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable( boolean available )
	{
		this.available = available;
	}

	private boolean available;

	public String getBookingId()
	{
		return bookingId;
	}

	public void setBookingId( String bookingId )
	{
		this.bookingId = bookingId;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate( String fromDate )
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate( String toDate )
	{
		this.toDate = toDate;
	}

	public String getCarNumber()
	{
		return carNumber;
	}

	public void setCarNumber( String carNumber )
	{
		this.carNumber = carNumber;
	}

	public String getTravelCity()
	{
		return travelCity;
	}

	public void setTravelCity( String travelCity )
	{
		this.travelCity = travelCity;
	}

	public String getCarAgency()
	{
		return carAgency;
	}

	public void setCarAgency( String carAgency )
	{
		this.carAgency = carAgency;
	}

	public String getCarAgencyPhoneNumber()
	{
		return carAgencyPhoneNumber;
	}

	public void setCarAgencyPhoneNumber( String carAgencyPhoneNumber )
	{
		this.carAgencyPhoneNumber = carAgencyPhoneNumber;
	}

	public String getDriverName()
	{
		return driverName;
	}

	public void setDriverName( String driverName )
	{
		this.driverName = driverName;
	}

	public String getDrivePhoneNumber()
	{
		return drivePhoneNumber;
	}

	public void setDrivePhoneNumber( String drivePhoneNumber )
	{
		this.drivePhoneNumber = drivePhoneNumber;
	}

	public String getCoupon()
	{
		return coupon;
	}

	public void setCoupon( String coupon )
	{
		this.coupon = coupon;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName( String customerName )
	{
		this.customerName = customerName;
	}

	public String getCustomerNumber()
	{
		return customerNumber;
	}

	public void setCustomerNumber( String customerNumber )
	{
		this.customerNumber = customerNumber;
	}

	public String getPickUpCity()
	{
		return pickUpCity;
	}

	public void setPickUpCity( String pickUpCity )
	{
		this.pickUpCity = pickUpCity;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress( String address )
	{
		this.address = address;
	}

	public String getCarLocation()
	{
		return carLocation;
	}

	public void setCarLocation( String carLocation )
	{
		this.carLocation = carLocation;
	}

	public String getCarType()
	{
		return carType;
	}

	public void setCarType( String carType )
	{
		this.carType = carType;
	}

	public int getCarCapacity()
	{
		return carCapacity;
	}

	public void setCarCapacity( int carCapacity )
	{
		this.carCapacity = carCapacity;
	}

	public String getCarModel()
	{
		return carModel;
	}

	public void setCarModel( String carModel )
	{
		this.carModel = carModel;
	}

	public String getCarRegisteredAt()
	{
		return carRegisteredAt;
	}

	public void setCarRegisteredAt( String carRegisteredAt )
	{
		this.carRegisteredAt = carRegisteredAt;
	}

	public int getMinimunDistancePerDay()
	{
		return minimunDistancePerDay;
	}

	public void setMinimunDistancePerDay( int minimunDistancePerDay )
	{
		this.minimunDistancePerDay = minimunDistancePerDay;
	}

	public int getPricePerKilometer()
	{
		return pricePerKilometer;
	}

	public void setPricePerKilometer( int pricePerKilometer )
	{
		this.pricePerKilometer = pricePerKilometer;
	}

	public String getCarName()
	{
		return carName;
	}

	public void setCarName( String carName )
	{
		this.carName = carName;
	}

	public String getLicenseNumber()
	{
		return licenseNumber;
	}

	public void setLicenseNumber( String licenseNumber )
	{
		this.licenseNumber = licenseNumber;
	}

}
