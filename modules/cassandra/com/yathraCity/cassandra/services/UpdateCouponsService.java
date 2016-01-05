package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.UpdateCoupenDAO;
import com.yathraCity.core.CoupenDeails;

public class UpdateCoupensService 
{
	public boolean updateCoupnesMethod(CoupenDeails coupenDetails)
	{
		boolean msg=false;
		UpdateCoupenDAO update=new UpdateCoupenDAO();
		msg=update.updateCoupensMeth(coupenDetails);
		return msg;
		
	}
}
