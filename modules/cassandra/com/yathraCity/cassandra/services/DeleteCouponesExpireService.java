package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.DeleteCoupensExpiredDAO;

public class DeleteCouponesExpireService 
{
	public boolean deleteMeth()
	{
		boolean msg=false;
		try
		{
			DeleteCoupensExpiredDAO delete=new DeleteCoupensExpiredDAO();
			msg=delete.deleteCoupens();
			return msg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return msg;
		}
	}
}
