package defaultpkg;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
@Provider
public class JsonCtxResolver implements ContextResolver<JAXBContext>  {
	private JAXBContext context;
	private Class[] types = {
			com.yathraCity.core.LoginInput.class,
			com.yathraCity.core.LoginResponse.class,
			com.yathraCity.core.ResponseMessage.class,
			com.yathraCity.core.LogInput.class,
			com.yathraCity.core.LogInputList.class,
			com.yathraCity.core.RequestObject.class,
			com.yathraCity.core.CouponDetails.class,
			com.yathraCity.core.ListOfCouponsUsed.class,
			com.yathraCity.core.RegisterBookingInput.class,
			com.yathraCity.core.RegisterUser.class,
			com.yathraCity.core.RegisterUserResponse.class,
			com.yathraCity.core.RegisterCarInput.class,
			com.yathraCity.core.AvailableCars.class,
			com.yathraCity.core.ListOfAvailableCars.class,
			com.yathraCity.core.CheckAvailabilityInput.class,
			com.yathraCity.core.RegisterCarDetails.class,
			com.yathraCity.core.RegisterCarResponse.class,
			com.yathraCity.core.TotalNumberOfUsers.class,
			com.yathraCity.core.Message.class,
			com.razorthink.runtime.SessionData.class,
			com.razorthink.runtime.query.Query.class,
			com.razorthink.runtime.query.Result.class,
			com.razorthink.runtime.query.Row.class,
			com.razorthink.runtime.query.BatchResult.class,
			com.razorthink.runtime.query.QueryResult.class
			};
	public JsonCtxResolver() throws Exception {
		this.context = new JSONJAXBContext(JSONConfiguration.natural().build(),types);
	}
	public JAXBContext getContext(Class c) {
		System.out.println(c.getCanonicalName());
		return(context);
	}
}
