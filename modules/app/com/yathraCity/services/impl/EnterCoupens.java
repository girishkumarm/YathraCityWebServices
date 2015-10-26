package com.yathraCity.services.impl;

import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.services.CoupenDetails;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.services.EnterCoupensInterface;

public class EnterCoupens implements EnterCoupensInterface
{

	@Override
	public RegisterCarResponse coupens( ServiceExecutionContext ctx, CoupenDeails coupensDetails ) throws ExecException
	{
		CoupenDetails newCoupen=new CoupenDetails();
		RegisterCarResponse response=new RegisterCarResponse();
		response.setMessage("could not add the new coupen");
		boolean coupenmsg=newCoupen.addingCoupensToDB(coupensDetails);
		if(coupenmsg==false)
		{
			
			return response;
		}
		else
		{
			response.setMessage("coupenadded successfully");
			return response;
		}
	}
	
}
