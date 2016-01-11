package defaultpkg;
import com.razorthink.runtime.*;
public class TestFacade  {

	public static String BASEURL="";

	public static com.razorthink.runtime.query.Result QueryService_runQuery(com.razorthink.runtime.query.Query query) throws Exception{
		String url = BASEURL+ "sqlquery" ;
		return (com.razorthink.runtime.query.Result) WebServiceTester.test(url, query, com.razorthink.runtime.query.Result.class, "POST");
	}

	public static com.yathraCity.core.RegisterUserResponse RegisterService_registerUser(com.yathraCity.core.RegisterUser input) throws Exception{
		String url = BASEURL + "register" + "/" + "register-user" ;
		return (com.yathraCity.core.RegisterUserResponse) WebServiceTester.test(url, input, com.yathraCity.core.RegisterUserResponse.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage Driver_registerdriver(com.yathraCity.core.DriverDetails driverDetails) throws Exception{
		String url = BASEURL + "driver" + "/" + "register-driver" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, driverDetails, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage Driver_updateDriverAvailability(com.yathraCity.core.ConfirmDriverAvailability driverDetails) throws Exception{
		String url = BASEURL + "driver" + "/" + "update-driver-availability" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, driverDetails, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage Car_registerCar(com.yathraCity.core.RegisterCarInput input) throws Exception{
		String url = BASEURL + "cars" + "/" + "register-car" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage Car_checkCarAvailability(com.yathraCity.core.CheckAvailabilityInput input) throws Exception{
		String url = BASEURL + "cars" + "/" + "check-car-availability" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ListOfAvailableCars Car_getCarDetails(com.yathraCity.core.FetchCarDetails input) throws Exception{
		String url = BASEURL + "cars" + "/" + "get-car-details" ;
		return (com.yathraCity.core.ListOfAvailableCars) WebServiceTester.test(url, input, com.yathraCity.core.ListOfAvailableCars.class, "POST");
	}

	public static com.yathraCity.core.Message CountingService_countNumberOfUsers() throws Exception{
		String url = BASEURL + "counting" + "/" + "count-number-users" ;
		return (com.yathraCity.core.Message) WebServiceTester.test(url, null, com.yathraCity.core.Message.class, "GET");
	}

	public static com.yathraCity.core.TotalNumberOfUsers NumberOfUsers_getNumberOfUsers() throws Exception{
		String url = BASEURL + "getnumberofusers" + "/" + "get-number-of-users" ;
		return (com.yathraCity.core.TotalNumberOfUsers) WebServiceTester.test(url, null, com.yathraCity.core.TotalNumberOfUsers.class, "GET");
	}

	public static com.yathraCity.core.ResponseMessage Coupons_coupons(com.yathraCity.core.CouponDetails couponsDetails) throws Exception{
		String url = BASEURL + "coupons" + "/" + "coupons-of-tab" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, couponsDetails, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ListOfCouponsUsed Coupons_getalltheCoupons() throws Exception{
		String url = BASEURL + "coupons" + "/" + "get-list-of-coupons" ;
		return (com.yathraCity.core.ListOfCouponsUsed) WebServiceTester.test(url, null, com.yathraCity.core.ListOfCouponsUsed.class, "GET");
	}

	public static com.yathraCity.core.ResponseMessage Coupons_updateCoupon(com.yathraCity.core.CouponDetails couponsDetails) throws Exception{
		String url = BASEURL + "coupons" + "/" + "update-coupons" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, couponsDetails, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage Coupons_deleteCoupons(String couponName) throws Exception{
		String url = BASEURL + "coupons" + "/" + "delete-coupons"  + "/" + couponName;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, null, com.yathraCity.core.ResponseMessage.class, "GET");
	}

	public static com.yathraCity.core.LoginResponse LoginService_loginUser(com.yathraCity.core.LoginInput input) throws Exception{
		String url = BASEURL + "login" + "/" + "login-user" ;
		return (com.yathraCity.core.LoginResponse) WebServiceTester.test(url, input, com.yathraCity.core.LoginResponse.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_bookCar(com.yathraCity.core.RegisterBookingInput input) throws Exception{
		String url = BASEURL + "yathra" + "/" + "book-car" ;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, input, com.yathraCity.core.ResponseMessage.class, "POST");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_getOTP(String phoneNumber) throws Exception{
		String url = BASEURL + "yathra" + "/" + "get-otp"  + "/" + phoneNumber;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, null, com.yathraCity.core.ResponseMessage.class, "GET");
	}

	public static com.yathraCity.core.ResponseMessage YathraService_getOTPMatchResponse(String phoneNumber,String otp) throws Exception{
		String url = BASEURL + "yathra" + "/" + "get-otp-match-response"  + "/" + phoneNumber + "/" + otp;
		return (com.yathraCity.core.ResponseMessage) WebServiceTester.test(url, null, com.yathraCity.core.ResponseMessage.class, "GET");
	}
}
