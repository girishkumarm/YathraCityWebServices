package com.yathraCity.cassandra.pojo;


public class BookingDetails
{
	private String userName;
	private String emailId;
	private long phoneNumber;
	private int numberOfPeoples;
	private String bookedCarName;
	private long carBookedAt;
	private String pickUpPoint;
	private int numberOfDays;
	private String bookedCarNumber;
	private String bookedCarOwner;
	private int discountOffered;
	private boolean bookingConfirmation;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId( String emailId )
	{
		this.emailId = emailId;
	}

	public long getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber( long phoneNumber )
	{
		this.phoneNumber = phoneNumber;
	}

	public int getNumberOfPeoples()
	{
		return numberOfPeoples;
	}

	public void setNumberOfPeoples( int numberOfPeoples )
	{
		this.numberOfPeoples = numberOfPeoples;
	}

	public String getBookedCarName()
	{
		return bookedCarName;
	}

	public void setBookedCarName( String bookedCarName )
	{
		this.bookedCarName = bookedCarName;
	}

	public long getCarBookedAt()
	{
		return carBookedAt;
	}

	public void setCarBookedAt( long carBookedAt )
	{
		this.carBookedAt = carBookedAt;
	}

	public String getPickUpPoint()
	{
		return pickUpPoint;
	}

	public void setPickUpPoint( String pickUpPoint )
	{
		this.pickUpPoint = pickUpPoint;
	}

	public int getNumberOfDays()
	{
		return numberOfDays;
	}

	public void setNumberOfDays( int numberOfDays )
	{
		this.numberOfDays = numberOfDays;
	}

	public String getBookedCarNumber()
	{
		return bookedCarNumber;
	}

	public void setBookedCarNumber( String bookedCarNumber )
	{
		this.bookedCarNumber = bookedCarNumber;
	}

	public String getBookedCarOwner()
	{
		return bookedCarOwner;
	}

	public void setBookedCarOwner( String bookedCarOwner )
	{
		this.bookedCarOwner = bookedCarOwner;
	}

	public int getDiscountOffered()
	{
		return discountOffered;
	}

	public void setDiscountOffered( int discountOffered )
	{
		this.discountOffered = discountOffered;
	}

	public boolean isBookingConfirmation()
	{
		return bookingConfirmation;
	}

	public void setBookingConfirmation( boolean bookingConfirmation )
	{
		this.bookingConfirmation = bookingConfirmation;
	}

}
