package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.pojo.CoupensPojo;
import com.yathraCity.cassandra.services.CoupenDetails;
import com.yathraCity.cassandra.services.DeleteCouponesExpireService;
import com.yathraCity.cassandra.services.ListOfAllCoupens;
import com.yathraCity.cassandra.services.UpdateCoupensService;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.core.ListOfCoupensUsed;
import com.yathraCity.core.ListOfCoupensUsed;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.services.CoupensInterface;
import com.yathraCity.services.CoupensInterface;

public class Coupens implements CoupensInterface{

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

	@Override
	public ListOfCoupensUsed getallthecupons( ServiceExecutionContext ctx ) throws ExecException
	{
		ListOfCoupensUsed coupens=new ListOfCoupensUsed();
		List<CoupensPojo> allCoupens=new ArrayList<CoupensPojo>();
		List<CoupenDeails> mycoupens=new ArrayList<CoupenDeails>();
		
		ListOfAllCoupens listOfAllCoupens=new ListOfAllCoupens();
		allCoupens=listOfAllCoupens.coupenListMeth();
		
		for(CoupensPojo coupenDetails:allCoupens)
		{
			CoupenDeails newCoupens=new CoupenDeails();
			newCoupens.setCoupen(coupenDetails.getCoupenName());
			System.out.println(coupenDetails.getCoupenName());
			newCoupens.setFromDate(coupenDetails.getValidFrom());
			System.out.println(coupenDetails.getValidFrom());
			newCoupens.setToDate(coupenDetails.getValidTo());
			System.out.println(coupenDetails.getValidTo());
			mycoupens.add(newCoupens);
		}
		coupens.setlistOfCoupens(mycoupens);
		return coupens;
	}

	@Override
	public RegisterCarResponse updateCoupen( ServiceExecutionContext ctx, CoupenDeails coupensDetails )
			throws ExecException
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

	@Override
	public RegisterCarResponse deleteCoupens( ServiceExecutionContext ctx, String couponName ) throws ExecException
	{
		boolean msg=false;
		RegisterCarResponse response=new RegisterCarResponse();
		DeleteCouponesExpireService delete=new DeleteCouponesExpireService();
		try
		{
			msg=delete.deleteMeth(couponName);
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
