package com.xiaoshu.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.service.db3.ShgxcService;
import com.xiaoshu.admin.shbfk.mapper.ShgxcMapper;

@Service
public class ShgxcServiceImpl implements ShgxcService{
	
	
	@Autowired
	private ShgxcMapper shgxcMapper;

	@Override
	public int findSlbh(ShMain shmain) {
		// TODO Auto-generated method stub
		return shgxcMapper.findSlbh(shmain);
	}

}
