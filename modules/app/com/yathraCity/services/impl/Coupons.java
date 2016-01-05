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
import defaultpkg.ErrorCodes;
import com.yathraCity.services.CoupensInterface;

/**
 * Adding the coupon
 * Deleting the coupon
 * Updating the coupon
 * listing all the coupons
 * @author ashwing
 *param coupon details
 */
public class Coupens implements CoupensInterface
{
	
	//Adding the new coupon by giving the coupon details
	@Override
	public RegisterCarResponse coupens( ServiceExecutionContext ctx, CoupenDeails coupensDetails ) throws ExecException
	{
		CoupenDetails newCoupen=new CoupenDetails();
		RegisterCarResponse response=new RegisterCarResponse();
		response.setMessage("could not add the new coupen");
		try
		{
			if(coupensDetails == null || coupensDetails.getCoupen() == null || coupensDetails.getCoupen().trim().isEmpty()
					|| coupensDetails.getFromDate()==null || coupensDetails.getFromDate().trim().isEmpty()
					|| coupensDetails.getToDate()==null || coupensDetails.getToDate().trim().isEmpty())
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			//adding coupen in the DB
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
		catch(Exception e)
		{
			e.printStackTrace();
			return response;
		}
	}

	//Getting all the coupons
	@Override
	public ListOfCoupensUsed getallthecupons( ServiceExecutionContext ctx ) throws ExecException
	{
		try
		{
			ListOfCoupensUsed coupens=new ListOfCoupensUsed();
			List<CoupensPojo> allCoupens=new ArrayList<CoupensPojo>();
			List<CoupenDeails> mycoupens=new ArrayList<CoupenDeails>();
			//getting all th coupons in the DB
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
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	//Update coupon details by passing the coupon details
	@Override
	public RegisterCarResponse updateCoupen( ServiceExecutionContext ctx, CoupenDeails coupensDetails )
			throws ExecException
	{
		RegisterCarResponse response=new RegisterCarResponse();
		response.setMessage("didnt get updated");
		
		UpdateCoupensService update=new UpdateCoupensService();
		boolean msg=false;
		try
		{
		
			if(coupensDetails == null || coupensDetails.getCoupen() == null || coupensDetails.getCoupen().trim().isEmpty()
					|| coupensDetails.getFromDate()==null || coupensDetails.getFromDate().trim().isEmpty()
					|| coupensDetails.getToDate()==null || coupensDetails.getToDate().trim().isEmpty())
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			//updating the coupons in the DB
			msg =update.updateCoupnesMethod(coupensDetails);
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
		catch(Exception e)
		{
			e.printStackTrace();
			return response;
		}
	}

	//deleting the coupon name by its name 
	@Override
	public RegisterCarResponse deleteCoupens( ServiceExecutionContext ctx, String couponName ) throws ExecException
	{
		boolean msg=false;
		RegisterCarResponse response=new RegisterCarResponse();
		DeleteCouponesExpireService delete=new DeleteCouponesExpireService();
		try
		{
			if(couponName==null || couponName.trim().isEmpty())
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			//deleting from DB
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