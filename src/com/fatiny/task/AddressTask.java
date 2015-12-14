package com.fatiny.task;

import java.util.Iterator;

import javax.annotation.Resource;

import com.fatiny.pojo.Visitor;
import com.fatiny.service.VisitorService;
import com.fatiny.util.AddressUtils;
import com.fatiny.vo.CommonData;

/**
 * @Description 解析客户端访问这得IP, 获取地域信息
 * @author Jeremy
 * @date 2015年12月3日 下午11:05:54 
 * @version V1.0
 */
public class AddressTask {
	private VisitorService visitorService;
	public VisitorService getVisitorService() {
		return visitorService;
	}
	@Resource
	public void setVisitorService(VisitorService visitorService) {
		this.visitorService = visitorService;
	}



	public void run(){
		Iterator<String> iter = CommonData.ipSet.iterator();
//		if(!CommonData.ipSet.isEmpty()){
//			System.out.println("Not null, size:"+CommonData.ipSet.size());
//		}else{
//			System.out.println("Null");
//		}
		if(iter.hasNext()){
			String ip = iter.next();
			iter.remove();
			String address = AddressUtils.getGeoAddress(ip);
//			System.out.println("address:"+address);
			//解析保存访问者信息
			Visitor visitor = new Visitor(ip, address);
			visitorService.saveOrUpdate(visitor);
		}
		
	}
	
}
