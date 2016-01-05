package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.pojo.CouponsPojo;
import com.yathraCity.cassandra.services.DeleteCouponesExpireService;
import com.yathraCity.cassandra.services.ListOfAllCoupons;
import com.yathraCity.cassandra.services.UpdateCouponsService;
import com.yathraCity.core.CouponDetails;
import com.yathraCity.core.ListOfCouponsUsed;
import com.yathraCity.core.RegisterCarResponse;
import com.yathraCity.services.CouponsInterface;
import defaultpkg.ErrorCodes;

/**
 * Adding the coupon
 * Deleting the coupon
 * Updating the coupon
 * listing all the coupons
 * 
 * @author ashwing
 *         param coupon details
 */
public class Coupons implements CouponsInterface {

	// Adding the new coupon by giving the coupon details
	@Override
	public RegisterCarResponse coupons( ServiceExecutionContext ctx, CouponDetails couponsDetails ) throws ExecException
	{
		com.yathraCity.cassandra.services.CouponDetails newCoupon = new com.yathraCity.cassandra.services.CouponDetails();
		RegisterCarResponse response = new RegisterCarResponse();
		response.setMessage("could not add the new coupon");
		try
		{
			if( couponsDetails == null || couponsDetails.getCoupon() == null
					|| couponsDetails.getCoupon().trim().isEmpty() || couponsDetails.getFromDate() == null
					|| couponsDetails.getFromDate().trim().isEmpty() || couponsDetails.getToDate() == null
					|| couponsDetails.getToDate().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			// adding coupon in the DB
			boolean couponmsg = newCoupon.addingCouponsToDB(couponsDetails);
			if( couponmsg == false )
			{

				return response;
			}
			else
			{
				response.setMessage("couponadded successfully");
				return response;
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return response;
		}
	}

	// Getting all the coupons
	@Override
	public ListOfCouponsUsed getalltheCoupons( ServiceExecutionContext ctx ) throws ExecException
	{
		try
		{
			ListOfCouponsUsed coupons = new ListOfCouponsUsed();
			List<CouponsPojo> allCoupons = new ArrayList<CouponsPojo>();
			List<CouponDetails> mycoupons = new ArrayList<CouponDetails>();
			// getting all th coupons in the DB
			ListOfAllCoupons listOfAllCoupons = new ListOfAllCoupons();
			allCoupons = listOfAllCoupons.couponListMeth();

			for( CouponsPojo couponDetails : allCoupons )
			{
				CouponDetails newCoupons = new CouponDetails();
				newCoupons.setCoupon(couponDetails.getCoupenName());
				System.out.println(couponDetails.getCoupenName());
				newCoupons.setFromDate(couponDetails.getValidFrom());
				System.out.println(couponDetails.getValidFrom());
				newCoupons.setToDate(couponDetails.getValidTo());
				System.out.println(couponDetails.getValidTo());
				mycoupons.add(newCoupons);
			}
			coupons.setlistOfCoupons(mycoupons);
			return coupons;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}

	// Update coupon details by passing the coupon details
	@Override
	public RegisterCarResponse updateCoupon( ServiceExecutionContext ctx, CouponDetails couponsDetails )
			throws ExecException
	{
		RegisterCarResponse response = new RegisterCarResponse();
		response.setMessage("didnt get updated");

		UpdateCouponsService update = new UpdateCouponsService();
		boolean msg = false;
		try
		{

			if( couponsDetails == null || couponsDetails.getCoupon() == null
					|| couponsDetails.getCoupon().trim().isEmpty() || couponsDetails.getFromDate() == null
					|| couponsDetails.getFromDate().trim().isEmpty() || couponsDetails.getToDate() == null
					|| couponsDetails.getToDate().trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			// updating the coupons in the DB
			msg = update.updateCoupnesMethod(couponsDetails);
			if( msg == false )
			{
				return response;
			}
			else
			{
				response.setMessage("updated successfully");
				return response;
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return response;
		}
	}

	// deleting the coupon name by its name
	@Override
	public RegisterCarResponse deleteCoupons( ServiceExecutionContext ctx, String couponName ) throws ExecException
	{
		boolean msg = false;
		RegisterCarResponse response = new RegisterCarResponse();
		DeleteCouponesExpireService delete = new DeleteCouponesExpireService();
		try
		{
			if( couponName == null || couponName.trim().isEmpty() )
			{
				throw new ExecException(ErrorCodes.MISSING_FIELD, null, "Mandatory Fields are missing");
			}
			// deleting from DB
			msg = delete.deleteMeth(couponName);
			if( msg == true )
			{
				response.setMessage("successfully deleted");
			}
			else
			{
				response.setMessage("not deleted");
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response.setMessage("not deleted");
		}
		return response;
	}
}