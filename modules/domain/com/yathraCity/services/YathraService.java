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
@Path("/yathra")
public class YathraService{
	@Context
	HttpServletRequest request;


	@Context
	HttpServletResponse response;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register-car")
	public com.yathraCity.core.ResponseMessage registerCar(com.yathraCity.core.RegisterCarInput input){
		com.yathraCity.core.ResponseMessage resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.registerCar(context,input);
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
	@Path("/book-car")
	public com.yathraCity.core.ResponseMessage bookCar(com.yathraCity.core.RegisterBookingInput input){
		com.yathraCity.core.ResponseMessage resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.bookCar(context,input);
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
	@Path("/check-car-availability")
	public com.yathraCity.core.ResponseMessage checkCarAvailability(com.yathraCity.core.CheckAvailabilityInput input){
		com.yathraCity.core.ResponseMessage resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.checkCarAvailability(context,input);
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
	@Path("/get-car-details")
	public com.yathraCity.core.ListOfAvailableCars getCarDetails(com.yathraCity.core.FetchCarDetails input){
		com.yathraCity.core.ListOfAvailableCars resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.getCarDetails(context,input);
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
	@Path("/get-otp/{phoneNumber}")
	public com.yathraCity.core.ResponseMessage getOTP(@PathParam("phoneNumber") String phoneNumber){
		com.yathraCity.core.ResponseMessage resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.getOTP(context,phoneNumber);
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
	@Path("/get-otp-match-response/{phoneNumber}/{otp}")
	public com.yathraCity.core.ResponseMessage getOTPMatchResponse(@PathParam("phoneNumber") String phoneNumber,@PathParam("otp") String otp){
		com.yathraCity.core.ResponseMessage resp=null;
		SessionUtils.clear(request);
		ServiceExecutionContext context=null;
		try
		{
			context = new ServiceExecutionContext(request);
			YathraServiceInterface intfc =  (YathraServiceInterface) (ServiceParser.getImpl("com.yathraCity.services.YathraService"));
			if (intfc == null) throw new ExecException(ErrorCodes.APPLICATION_ERROR,null,"Service not implemented");
			resp =  intfc.getOTPMatchResponse(context,phoneNumber,otp);
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
