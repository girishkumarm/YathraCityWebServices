package defaultpkg;
import java.util.HashMap;
public class ServiceParser {

	private static boolean inited = false;
	private static HashMap<String,String> IMPL_MAP = new HashMap<String,String>();

	public static void init(){
		if (inited) return;
		IMPL_MAP.put("com.yathraCity.services.LoginService","com.yathraCity.services.impl.LoginService");
		IMPL_MAP.put("com.yathraCity.services.RegisterService","com.yathraCity.services.impl.RegisterService");
		IMPL_MAP.put("com.yathraCity.services.YathraService","com.yathraCity.services.impl.YathraService");
		IMPL_MAP.put("com.yathraCity.services.Coupens","com.yathraCity.services.impl.Coupens");
		IMPL_MAP.put("com.yathraCity.services.RegisterDriver","com.yathraCity.services.impl.RegisterDriver");
		IMPL_MAP.put("com.yathraCity.services.CountingService","com.yathraCity.services.impl.CountingService");
		IMPL_MAP.put("com.yathraCity.services.NumberOfUsers","com.yathraCity.services.impl.NumberOfUsers");
		inited = true;
	}


	public static Object getImpl(String abstractClassName) throws Exception
	{
		init();
		String clsName = IMPL_MAP.get(abstractClassName.trim());
		if (clsName == null) return null;
		try{return Class.forName(clsName).newInstance();} catch (Exception e) {throw e;}
	}
}
