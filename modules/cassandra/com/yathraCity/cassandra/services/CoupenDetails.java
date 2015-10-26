package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.AddCoupensDetailsDAO;
import com.yathraCity.core.CoupenDeails;

public class CoupenDetails 
{
	public boolean addingCoupensToDB(CoupenDeails mycoupen)
	{
		boolean cupon=false;
		try
		{
		
			AddCoupensDetailsDAO adding=new AddCoupensDetailsDAO();
			if(mycoupen.getCoupen()!=null || mycoupen.getCoupen().trim()!=" "
					|| mycoupen.getFromDate()!=null || mycoupen.getFromDate().trim()!=" "
					|| mycoupen.getToDate()!=null || mycoupen.getToDate().trim()!= " ")
			{
				cupon=adding.addCoupens(mycoupen);
			}
			else
			{
				return cupon;
			}
			return cupon;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return cupon;
		}
	}
}
