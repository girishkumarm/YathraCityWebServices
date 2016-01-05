package com.yathraCity.cassandra.services;

import java.util.ArrayList;
import java.util.List;
import com.yathraCity.cassandra.dao.AllCoupensDetails;
import com.yathraCity.cassandra.pojo.CouponsPojo;

public class ListOfAllCoupons 
{
	public List<CouponsPojo> couponListMeth()
	{
		List<CouponsPojo> listOfCup=new ArrayList<CouponsPojo>();
		try
		{
			AllCoupensDetails allCoupons=new AllCoupensDetails();
			listOfCup=allCoupons.listOfAllCoupenDetails();
			return listOfCup;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return listOfCup;
		}
	}
}
