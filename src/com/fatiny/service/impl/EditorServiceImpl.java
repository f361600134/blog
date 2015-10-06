package com.fatiny.service.impl;

import org.springframework.stereotype.Component;

import com.fatiny.pojo.Editor;
import com.fatiny.service.EditorService;

@Component
public class EditorServiceImpl implements EditorService{

	@Override
	public void saveOrUpdate(Editor editor) {
		// TODO Auto-generated method stub
		System.out.println("保存...editor="+editor);
	}
	
}
