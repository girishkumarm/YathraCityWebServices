package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.DeleteCouponesExpireService;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.services.DeleteCoupensExpiredInterface;

public class DeleteCoupensExpired implements DeleteCoupensExpiredInterface
{

	@Override
	public RegisterCarResponse deleteCoupens( ServiceExecutionContext ctx ) throws ExecException
	{
		boolean msg=false;
		RegisterCarResponse response=new RegisterCarResponse();
		DeleteCouponesExpireService delete=new DeleteCouponesExpireService();
		try
		{
			msg=delete.deleteMeth();
			if(msg==true)
			{
				response.setMessage("successfully deleted");
			}
			else
			{
				response.setMessage("not deleted");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.setMessage("not deleted");
		}
		return response;
	}

}
