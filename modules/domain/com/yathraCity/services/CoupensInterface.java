package com.yathraCity.services;
import com.razorthink.runtime.*;
import javax.ws.rs.core.StreamingOutput;
public interface CoupensInterface{
	public com.yathraCity.core.RegisterCarResponse coupens(ServiceExecutionContext ctx,com.yathraCity.core.CoupenDeails coupensDetails) throws ExecException;


	public com.yathraCity.core.ListOfCoupensUsed getallthecupons(ServiceExecutionContext ctx) throws ExecException;


	public com.yathraCity.core.RegisterCarResponse updateCoupen(ServiceExecutionContext ctx,com.yathraCity.core.CoupenDeails coupensDetails) throws ExecException;


	public com.yathraCity.core.RegisterCarResponse deleteCoupens(ServiceExecutionContext ctx,String couponName) throws ExecException;


}
