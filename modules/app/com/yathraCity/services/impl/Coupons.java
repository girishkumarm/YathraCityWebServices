package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.dao.CouponsDAO;
import com.yathraCity.cassandra.pojo.Coupon;
import com.yathraCity.core.CouponDetails;
import com.yathraCity.core.CouponsList;
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
public class Coupons implements CouponsInterface {

	ResponseMessage response = new ResponseMessage();
	CouponsDAO couponDAO = new CouponsDAO();
	private static Logger logger = LoggerFactory.getLogger(Coupons.class);

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
			boolean couponFlag = couponDAO.addCoupons(couponsDetails);
			if( couponFlag == true )
			{
				response.setMessage("couponadded successfully");
				response.setStatus("200");
			}
		}
		catch( ExecException m )
		{
			logger.error("Error while adding the coupen" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while adding the coupen" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
		}
		return response;
	}

	// Getting all the coupons
	@Override
	public CouponsList getalltheCoupons( ServiceExecutionContext ctx ) throws ExecException
	{
		CouponsList coupons = new CouponsList();
		try
		{

			List<Coupon> allCoupons = new ArrayList<Coupon>();
			List<CouponDetails> mycoupons = new ArrayList<CouponDetails>();
			// getting all th coupons in the DB
			allCoupons = couponDAO.getListOfAllCoupenDetails();

			for( Coupon couponDetails : allCoupons )
			{
				CouponDetails newCoupons = new CouponDetails();
				newCoupons.setCoupon(couponDetails.getCoupenName());
				newCoupons.setFromDate(couponDetails.getValidFrom());
				newCoupons.setToDate(couponDetails.getValidTo());
				newCoupons.setDiscount(couponDetails.getDiscount());
				mycoupons.add(newCoupons);
			}
			coupons.setlistOfCoupons(mycoupons);

		}
		catch( ExecException m )
		{
			logger.error("Error while getting the coupen" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while getting the coupen" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
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
			msg = couponDAO.updateCoupon(couponsDetails);
			if( msg == true )
			{
				response.setMessage("updated successfully");
				response.setStatus("200");
			}
		}
		catch( ExecException m )
		{
			logger.error("Error while updating the coupen" + m.getMessage());
			throw m;
		}
		catch( Exception e )
		{
			logger.error("Error while updating the coupen" + e.getMessage());
			throw new ExecException(ErrorCodes.APPLICATION_ERROR, e, e.getMessage());
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
			msg = couponDAO.deleteCoupens(couponName);
			if( msg == true )
			{
				response.setMessage("successfully deleted");
				response.setStatus("200");
			}
		}
		catch( Exception e )
		{
			logger.error("Error while deleting the coupen" + e.getMessage());
		}
		return response;
	}
}