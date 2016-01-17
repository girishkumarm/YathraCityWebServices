package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.AddCouponsDetailsDAO;

public class CouponDetails {

	public boolean addingCouponsToDB( com.yathraCity.core.CouponDetails mycoupen )
	{
		boolean cupon = false;
		try
		{

			AddCouponsDetailsDAO adding = new AddCouponsDetailsDAO();
			if( mycoupen.getCoupon() != null || mycoupen.getCoupon().trim() != " " || mycoupen.getFromDate() != null
					|| mycoupen.getFromDate().trim() != " " || mycoupen.getToDate() != null
					|| mycoupen.getToDate().trim() != " " )
			{
				cupon = adding.addCoupons(mycoupen);
			}
			else
			{
				return cupon;
			}
			return cupon;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return cupon;
		}
	}
}
