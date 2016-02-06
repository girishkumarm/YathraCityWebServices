package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.pojo.CouponsPojo;
import com.yathraCity.cassandra.services.DeleteCouponesExpireService;
import com.yathraCity.cassandra.services.ListOfAllCoupons;
import com.yathraCity.cassandra.services.UpdateCouponsService;
import com.yathraCity.core.CouponDetails;
import com.yathraCity.core.ListOfCouponsUsed;
import com.yathraCity.core.ResponseMessage;
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
public class Coupons implements CouponsInterface 
{
	ResponseMessage response = new ResponseMessage();
	DeleteCouponesExpireService delete = new DeleteCouponesExpireService();
	ListOfAllCoupons listOfAllCoupons = new ListOfAllCoupons();
	UpdateCouponsService update = new UpdateCouponsService();
	com.yathraCity.cassandra.services.CouponDetails newCoupon = new com.yathraCity.cassandra.services.CouponDetails();
	private static Logger logger = LoggerFactory.getLogger( Coupons.class );
	// Adding the new coupon by giving the coupon details
	@Override
	public ResponseMessage coupons( ServiceExecutionContext ctx, CouponDetails couponsDetails ) throws ExecException
	{
		response.setMessage("could not add the new coupon");
		response.setMessage("coupen is not added");
		response.setStatus("500");
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
			if( couponmsg == true )
			{
				response.setMessage("couponadded successfully");
				response.setStatus("200");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while adding the coupen"
					+ e.getMessage() );
		}
		return response;
	}

	// Getting all the coupons
	@Override
	public ListOfCouponsUsed getalltheCoupons( ServiceExecutionContext ctx ) throws ExecException
	{
		ListOfCouponsUsed coupons = new ListOfCouponsUsed();
		try
		{
			
			List<CouponsPojo> allCoupons = new ArrayList<CouponsPojo>();
			List<CouponDetails> mycoupons = new ArrayList<CouponDetails>();
			// getting all th coupons in the DB
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
			
		}
		catch( Exception e )
		{
			logger.error( "Error while getting the coupen"
					+ e.getMessage() );
		}
		return coupons;
	}

	// Update coupon details by passing the coupon details
	@Override
	public ResponseMessage updateCoupon( ServiceExecutionContext ctx, CouponDetails couponsDetails )
			throws ExecException
	{
		
		response.setMessage("didnt get updated");
		response.setStatus("500");
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
			if( msg == true )
			{
				response.setMessage("updated successfully");
				response.setStatus("200");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while updating the coupen"
					+ e.getMessage() );
		}
		return response;
	}

	// deleting the coupon name by its name
	@Override
	public ResponseMessage deleteCoupons( ServiceExecutionContext ctx, String couponName ) throws ExecException
	{
		boolean msg = false;
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
				response.setStatus("200");
			}
		}
		catch( Exception e )
		{
			logger.error( "Error while deleting the coupen"
					+ e.getMessage() );
		}
		return response;
	}
}