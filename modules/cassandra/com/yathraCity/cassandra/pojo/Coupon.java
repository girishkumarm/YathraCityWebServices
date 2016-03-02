package com.yathraCity.cassandra.pojo;

public class Coupon {

	private String coupenName;
	private String validFrom;
	private String validTo;
	private String discount;

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

	public String getDiscount()
	{
		return discount;
	}

	public void setDiscount( String discount )
	{
		this.discount = discount;
	}
}
