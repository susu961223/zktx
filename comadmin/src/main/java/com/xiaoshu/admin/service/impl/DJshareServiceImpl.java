package com.xiaoshu.admin.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.DJshare;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.oracle.mapper.BdcsjMapper;
import com.xiaoshu.admin.oracle.mapper.DJshareMapper;
import com.xiaoshu.admin.service.db2.BdcsjService;
import com.xiaoshu.admin.service.db2.DJshareService;

@SuppressWarnings("unused")
@Service
public class DJshareServiceImpl implements DJshareService{
	
	@Autowired
	private DJshareMapper dJshareMapper;

	@Override
	public List<DJshare> selectDJshare(DJshare dJshare) {
		// TODO Auto-generated method stub
		return dJshareMapper.selectDJshare(dJshare);
	}

	@Override
	public Integer findCountDJshare(DJshare dJshare) {
		// TODO Auto-generated method stub
		return dJshareMapper.findCountDJshare(dJshare);
	}
	@Override
	public List<DJshare> selectQsxxList(DJshare dJshare) {
		// TODO Auto-generated method stub
		return dJshareMapper.selectQsxxList(dJshare);
	}

	@Override
	public List<DJshare> selectQsxxYwrList(DJshare dJshare) {
		// TODO Auto-generated method stub
		return dJshareMapper.selectQsxxYwrList(dJshare);
	}

	@Override
	public List<DJshare> selectDJshareAll(DJshare dJshare) {
		// TODO Auto-generated method stub
		return dJshareMapper.selectDJshareAll(dJshare);
	}
	
	
}
