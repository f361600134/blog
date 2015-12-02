package com.fatiny.task;

import java.util.Iterator;

import com.fatiny.util.AddressUtils;
import com.fatiny.vo.CommonData;

public class AddressTask {
	
	public void run(){
		Iterator<String> iter = CommonData.ipSet.iterator();
		System.out.println(CommonData.ipSet.size());
		if(!CommonData.ipSet.isEmpty()){
			System.out.println("Not null");
		}else{
			System.out.println("Null");
		}
		while(iter.hasNext()){
			String ip = iter.next();
			String address = AddressUtils.getGeoAddress(ip);
			System.out.println("address:"+address);
		}
		
	}
	
}
