package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface CouponsInterface{
	public com.yathraCity.core.ResponseMessage coupons(ServiceExecutionContext ctx,com.yathraCity.core.CouponDetails couponsDetails) throws ExecException;


	public com.yathraCity.core.ListOfCouponsUsed getalltheCoupons(ServiceExecutionContext ctx) throws ExecException;


	public com.yathraCity.core.ResponseMessage updateCoupon(ServiceExecutionContext ctx,com.yathraCity.core.CouponDetails couponsDetails) throws ExecException;


	public com.yathraCity.core.ResponseMessage deleteCoupons(ServiceExecutionContext ctx,String couponName) throws ExecException;


}
