package com.yathraCity.cassandra.services;

import com.yathraCity.cassandra.dao.UpdateCouponDAO;
import com.yathraCity.core.CouponDetails;

public class UpdateCouponsService {

	public boolean updateCoupnesMethod( CouponDetails coupenDetails )
	{
		boolean msg = false;
		UpdateCouponDAO update = new UpdateCouponDAO();
		msg = update.updateCouponsMeth(coupenDetails);
		return msg;

	}
}
