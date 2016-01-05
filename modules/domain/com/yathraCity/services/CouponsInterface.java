package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface CouponsInterface{
	public com.yathraCity.core.RegisterCarResponse coupons(ServiceExecutionContext ctx,com.yathraCity.core.CouponDetails couponsDetails) throws ExecException;


	public com.yathraCity.core.ListOfCouponsUsed getalltheCoupons(ServiceExecutionContext ctx) throws ExecException;


	public com.yathraCity.core.RegisterCarResponse updateCoupon(ServiceExecutionContext ctx,com.yathraCity.core.CouponDetails couponsDetails) throws ExecException;


	public com.yathraCity.core.RegisterCarResponse deleteCoupons(ServiceExecutionContext ctx,String couponName) throws ExecException;


}
