package com.yathraCity.cassandra.services;

import java.util.ArrayList;
import java.util.List;
import com.yathraCity.cassandra.dao.AllCoupensDetails;
import com.yathraCity.cassandra.pojo.CoupensPojo;

public class ListOfAllCoupens 
{
	public List<CoupensPojo> coupenListMeth()
	{
		List<CoupensPojo> listOfCup=new ArrayList<CoupensPojo>();
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
