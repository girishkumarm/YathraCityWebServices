package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.UpdateCoupensService;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.services.UpdateCoupensInterface;

public class UpdateCoupens implements UpdateCoupensInterface 
{
	@Override
	public RegisterCarResponse updateCoupen( ServiceExecutionContext ctx, CoupenDeails coupensDetails ) throws ExecException
	{
		RegisterCarResponse response=new RegisterCarResponse();
		response.setMessage("didnt get updated");
		
		UpdateCoupensService update=new UpdateCoupensService();
		boolean msg=update.updateCoupnesMethod(coupensDetails);
		if(msg==false)
		{
			return response;
		}
		else
		{
			response.setMessage("updated successfully");
			return response;
		}
	}
}
