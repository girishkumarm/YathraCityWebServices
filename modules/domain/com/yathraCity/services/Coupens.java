package com.yathraCity.services;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.ws.handler.MessageContext;
import javax.ws.rs.WebApplicationException;
import com.razorthink.runtime.*;
import defaultpkg.*;
@Path("/coupens")
public class Coupens{
	@Context
	HttpServletRequest request;


	@Context
	HttpServletResponse response;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coupens-of-tab")
	public com.yathraCity.core.RegisterCarResponse coupens(com.yathraCity.core.CoupenDeails coupensDetails){
		com.yathraCity.core.RegisterCarResponse resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			CoupensInterface intfc =  (CoupensInterface) (ServiceParser.getImpl("com.yathraCity.services.Coupens"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.coupens(context,coupensDetails);
			SessionUtils.clear(request);
			return resp;
		} catch (ExecException ee)
		{
			ee.printStackTrace(); // needs to be logged
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		} catch (Exception e)
		{
			e.printStackTrace(); // needs to be logged
			ExecException ee = new ExecException(ErrorCodes.APPLICATION_ERROR,e,"Application Error");
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		}
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-list-of-cupons")
	public com.yathraCity.core.ListOfCoupensUsed getallthecupons(){
		com.yathraCity.core.ListOfCoupensUsed resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			CoupensInterface intfc =  (CoupensInterface) (ServiceParser.getImpl("com.yathraCity.services.Coupens"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.getallthecupons(context);
			SessionUtils.clear(request);
			return resp;
		} catch (ExecException ee)
		{
			ee.printStackTrace(); // needs to be logged
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		} catch (Exception e)
		{
			e.printStackTrace(); // needs to be logged
			ExecException ee = new ExecException(ErrorCodes.APPLICATION_ERROR,e,"Application Error");
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		}
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update-coupens")
	public com.yathraCity.core.RegisterCarResponse updateCoupen(com.yathraCity.core.CoupenDeails coupensDetails){
		com.yathraCity.core.RegisterCarResponse resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			CoupensInterface intfc =  (CoupensInterface) (ServiceParser.getImpl("com.yathraCity.services.Coupens"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.updateCoupen(context,coupensDetails);
			SessionUtils.clear(request);
			return resp;
		} catch (ExecException ee)
		{
			ee.printStackTrace(); // needs to be logged
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		} catch (Exception e)
		{
			e.printStackTrace(); // needs to be logged
			ExecException ee = new ExecException(ErrorCodes.APPLICATION_ERROR,e,"Application Error");
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		}
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete-coupens/{couponName}")
	public com.yathraCity.core.RegisterCarResponse deleteCoupens(@PathParam("couponName") String couponName){
		com.yathraCity.core.RegisterCarResponse resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			CoupensInterface intfc =  (CoupensInterface) (ServiceParser.getImpl("com.yathraCity.services.Coupens"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.deleteCoupens(context,couponName);
			SessionUtils.clear(request);
			return resp;
		} catch (ExecException ee)
		{
			ee.printStackTrace(); // needs to be logged
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		} catch (Exception e)
		{
			e.printStackTrace(); // needs to be logged
			ExecException ee = new ExecException(ErrorCodes.APPLICATION_ERROR,e,"Application Error");
			request.setAttribute("WS_ERROR_MSG", ee.toJson());
			throw ee;
		}
	}


}
