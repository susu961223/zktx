package com.xiaoshu.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.FjEntity;
import com.xiaoshu.admin.oracle.mapper.FjMapper;
import com.xiaoshu.admin.service.db2.*;

@Service
public class FjServiceImpl implements FjService{
	
	@Autowired
	private FjMapper fjMapper;

	@Override
	public List<FjEntity> findFj(FjEntity fjEntity) {
		// TODO Auto-generated method stub
		return fjMapper.findFj(fjEntity);
	}

}
