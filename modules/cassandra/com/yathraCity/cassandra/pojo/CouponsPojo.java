package com.yathraCity.cassandra.pojo;


public class CouponsPojo 
{
	private String coupenName;
	private String validFrom;
	private String validTo;
	
	public String getCoupenName()
	{
		return coupenName;
	}
	
	public void setCoupenName( String coupenName )
	{
		this.coupenName = coupenName;
	}
	
	public String getValidFrom()
	{
		return validFrom;
	}
	
	public void setValidFrom( String validFrom )
	{
		this.validFrom = validFrom;
	}
	
	public String getValidTo()
	{
		return validTo;
	}
	
	public void setValidTo( String validTo )
	{
		this.validTo = validTo;
	}
}
