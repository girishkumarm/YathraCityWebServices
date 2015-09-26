package defaultpkg;
import com.razorthink.runtime.*;
public class TestFacade  {

	public static String BASEURL="";

	public static com.razorthink.runtime.query.Result QueryService_runQuery(com.razorthink.runtime.query.Query query) throws Exception{
		String url = BASEURL+ "sqlquery" ;
		return (com.razorthink.runtime.query.Result) WebServiceTester.test(url, query, com.razorthink.runtime.query.Result.class, "POST");
	}

	public static com.yathraCity.core.LoginResponse LoginService_loginUser(com.yathraCity.core.LoginInput input) throws Exception{
		String url = BASEURL + "login" + "/" + "login-user" ;
		return (com.yathraCity.core.LoginResponse) WebServiceTester.test(url, input, com.yathraCity.core.LoginResponse.class, "POST");
	}

	public static com.yathraCity.core.RegisterUserResponse RegisterService_registerUser(com.yathraCity.core.RegisterUser input) throws Exception{
		String url = BASEURL + "register" + "/" + "register-user" ;
		return (com.yathraCity.core.RegisterUserResponse) WebServiceTester.test(url, input, com.yathraCity.core.RegisterUserResponse.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_registerCar(com.yathraCity.core.RegisterCarInput input) throws Exception{
		String url = BASEURL + "register" + "/" + "register-car" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_bookCar(com.yathraCity.core.RegisterBookingInput input) throws Exception{
		String url = BASEURL + "register" + "/" + "book-car" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_checkCarAvailability(com.yathraCity.core.CheckAvailabilityInput input) throws Exception{
		String url = BASEURL + "register" + "/" + "check-car-availability" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ListOfAvailableCars YathraService_getCarDetails(String pickUpPoint,String capacity) throws Exception{
		String url = BASEURL + "register" + "/" + "get-car-details"  + "/" + pickUpPoint + "/" + capacity;
		return (com.yathraCity.core.ListOfAvailableCars) WebServiceTester.test(url, null, com.yathraCity.core.ListOfAvailableCars.class, "GET");
	}
}
