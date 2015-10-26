package com.yathraCity.cassandra.tables;
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
@Path("/getlistofcoupens")
public class ListOfCoupensInTab{
	@Context
	HttpServletRequest request;


	@Context
	HttpServletResponse response;


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
			ListOfCoupensInTabInterface intfc =  (ListOfCoupensInTabInterface) (ServiceParser.getImpl("com.yathraCity.services.ListOfCoupensInTab"));
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


}
