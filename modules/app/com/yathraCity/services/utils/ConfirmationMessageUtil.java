package com.yathraCity.services.utils;

import com.yathraCity.core.BookedCarDetails;

public class ConfirmationMessageUtil {

	public static String getConfirmationMessage( BookedCarDetails details )
	{
		String bodyHtml = "";
		try
		{
			bodyHtml = "<html lang=\"en-IN\"><body class=\"init default-theme des-mat\" "
					+ "style=\"background: rgb(255, 255, 255);\">"
					+ "<div id=\":1sm\" class=\"ii gt m1530c8daa9ffa091 adP adO\">"
					+ "<div id=\":1p1\" class=\"a3s\" style=\"overflow: hidden;\">"
					+ "<u></u><div><table style=\"width:100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
					+ "<tbody>" + "<tr>" + "<td style=\"padding:0;margin:0;background-color:#ffffff\" align=\"center\">"
					+ "<table style=\"max-width:610px\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "<tbody>"
					+ "<tr>"
					+ "<td style=\"text-align:left;vertical-align:top;font-size:0;padding-top:23px;padding-bottom:23px;padding-left:20px;background-color:#e2714c\">"
					+ "<a href=\"http://tabcars.in/\" target=\"_blank\"><img src=\"./tabcarsConfirmation_files/NZRB-Logo.jpg\" style=\"min-height:125px;min-height:22px;font-family:Helvetica,Arial,sans-serif;color:#000000;font-size:16px;font-weight:bold\" alt=\"TAB\" title=\"TAB\" height=\"\" width=\"\" class=\"CToWUd\"></a>"
					+ "</td>" + "</tr>" + "<tr>"
					+ "<td style=\"padding:0px 20px 20px 20px;margin:0;background-color:#f0f4f5\">"
					+ "<table style=\"width:100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" + "<tbody>"
					+ "<tr>"
					+ "<td style=\"padding:0;margin:0;background-color:#ffffff;padding-top:px;padding-bottom:px\" align=\"center\">"
					+ "<table style=\"width:100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" + "<tbody>"
					+ "<tr>"
					+ "<td style=\"padding:0;margin:0;font-family:Clan,'Helvetica Neue',Helvetica,Arial,sans-serif;font-size:26px;color:#000000;line-height:34px;padding:0px 0px\" align=\"center\">"
					+ "</td>" + "</tr>" + "<tr>" + "<td style=\"padding:0;margin:0\">" + "</td>" + "</tr>" + "<tr>"
					+ "<td style=\"padding:20px 20px 2px 20px;margin:0;border-right:2px solid #e3e3e3;border-left:2px solid #e3e3e3;border-bottom:2px solid #e3e3e3\" align=\"center\">"
					+ "<table bgcolor=\"\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + "<tbody>"
					+ "<tr>" + "<td>"
					+ "<table bgcolor=\"\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr><td style=\"font-family:Arial;font-size:13px\">"
					+ "<span>" + "<table style=\"width:100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
					+ "<tbody>" + "<tr>"
					+ "<td style=\"padding:0px;margin:0px;font-size:19px;line-height:30px;color:#999999;font-family:HelveticaNeue-Light,'Helvetica Neue Light','Helvetica Regular',Arial,sans-serif;padding:0px 0px\" align=\"left\">"
					+ "<p style=\"font-size:19px;line-height:30px;color:#999999;font-family:HelveticaNeue-Light,'Helvetica Neue Light','Helvetica Regular',Arial,sans-serif\">"
					+ "Hi " + details.getCustomerName() + "," + "<br></p>"
					+ "<p style=\"padding:0px;margin:0px;font-size:19px;line-height:30px;color:#999999;font-family:HelveticaNeue-Light,'Helvetica Neue Light','Helvetica Regular',Arial,sans-serif\">"
					+ "Your booking for car in tab cars has been confirmed as per the dates you have taken up. Have a beautiful journey. All the car details will be sent 1 day prior to your journey"
					+ "</p>" + "</td>" + "</tr>" + "<tr>" + "<td bgcolor=\"#ffffff\" height=\"30\">"
					+ "</td></tr></tbody></table></span></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td style=\"text-align:center;padding-bottom:px\"></td></tr><tr><td bgcolor=\"#ffffff\" height=\"\"></td></tr><tr><td bgcolor=\"#ffffff\" height=\"\" style=\"border-bottom:px solid #e3e3e3;border-right:2px solid #e3e3e3;border-left:2px solid #e3e3e3;border-bottom-left-radius:5px;border-bottom-right-radius:5px\"></td></tr><tr><td style=\"padding:0;margin:0;background-color:#f0f4f5\" align=\"center\"><table style=\"width:86%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td style=\"text-align:center;padding-bottom:5px\"></td></tr><tr><td bgcolor=\"\" height=\"\"></td></tr></tbody>"
					+ "</table></td></tr></tbody></table></td></tr><tr><td bgcolor=\"#ffffff\" height=\"\"></td></tr></tbody></table>"
					+ "</td></tr></tbody></table></td></tr></tbody></table></div></div></div></body></html>";
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return bodyHtml;
	}

}
