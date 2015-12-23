package com.fatiny.task;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;

import com.fatiny.pojo.Visitor;
import com.fatiny.service.VisitorService;
import com.fatiny.util.LogContext;
import com.fatiny.vo.CommonData;

/**
 * @Description 从内存中获取到所有的访问者,保存入库
 * @author Jeremy
 * @date 2015年12月3日 下午11:05:54 
 * @version V1.0
 */
public class AddressTask {
	private static Logger log = LogContext.LOG_MODULE_INTERCEPTER;
	
	private VisitorService visitorService;
	public VisitorService getVisitorService() {
		return visitorService;
	}
	@Resource
	public void setVisitorService(VisitorService visitorService) {
		this.visitorService = visitorService;
	}

	public void run(){
		Iterator<Entry<String, Visitor>> iter = CommonData.visitorMap.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Visitor> entry = iter.next();
			Visitor visitor = entry.getValue();
			log.info("visitor:" + visitor);
			visitorService.saveOrUpdate(visitor);
			iter.remove();
		}
	}
}
