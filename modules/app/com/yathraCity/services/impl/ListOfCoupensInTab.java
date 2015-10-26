package com.yathraCity.services.impl;

import java.util.ArrayList;
import java.util.List;
import com.razorthink.runtime.ExecException;
import com.razorthink.runtime.ServiceExecutionContext;
import com.yathraCity.cassandra.pojo.CoupensPojo;
import com.yathraCity.cassandra.services.ListOfAllCoupens;
import com.yathraCity.core.CoupenDeails;
import com.yathraCity.core.ListOfCoupensUsed;
import com.yathraCity.services.ListOfCoupensInTabInterface;

public class ListOfCoupensInTab implements ListOfCoupensInTabInterface
{

	@Override
	public ListOfCoupensUsed getallthecupons( ServiceExecutionContext ctx ) throws ExecException
	{
		ListOfCoupensUsed coupens=new ListOfCoupensUsed();
		List<CoupensPojo> allCoupens=new ArrayList<CoupensPojo>();
		List<CoupenDeails> mycoupens=new ArrayList<CoupenDeails>();
		
		ListOfAllCoupens listOfAllCoupens=new ListOfAllCoupens();
		allCoupens=listOfAllCoupens.coupenListMeth();
		
		for(CoupensPojo coupenDetails:allCoupens)
		{
			CoupenDeails newCoupens=new CoupenDeails();
			newCoupens.setCoupen(coupenDetails.getCoupenName());
			System.out.println(coupenDetails.getCoupenName());
			newCoupens.setFromDate(coupenDetails.getValidFrom());
			System.out.println(coupenDetails.getValidFrom());
			newCoupens.setToDate(coupenDetails.getValidTo());
			System.out.println(coupenDetails.getValidTo());
			mycoupens.add(newCoupens);
		}
		coupens.setlistOfCoupens(mycoupens);
		return coupens;
	}

}
