package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.DeleteCouponsDAO;

public class DeleteCouponesExpireService 
{
	public boolean deleteMeth(String coupenName)
	{
		boolean msg=false;
		try
		{
			DeleteCouponsDAO delete=new DeleteCouponsDAO();
			msg=delete.deleteCoupens(coupenName);
			return msg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return msg;
		}
	}
}
