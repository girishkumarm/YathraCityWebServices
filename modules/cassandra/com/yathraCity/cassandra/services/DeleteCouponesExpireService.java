package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.DeleteCoupensDAO;

public class DeleteCouponesExpireService 
{
	public boolean deleteMeth(String coupenName)
	{
		boolean msg=false;
		try
		{
			DeleteCoupensDAO delete=new DeleteCoupensDAO();
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
