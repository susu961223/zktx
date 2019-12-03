package com.xiaoshu.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.mapper.ShMainSwMapper;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;
import com.xiaoshu.admin.oracle.mapper.BdcFileMapper;
import com.xiaoshu.admin.oracle.mapper.ShMainMapper;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.db2.BdcFileService;
import com.xiaoshu.admin.service.db2.ShMainService;

@Service
public class ShMainServiceImp implements ShMainService {

	@Autowired
	private ShMainMapper shMainMapper;

	@Override
	public List<ShMain> selectBdcAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainMapper.selectBdcAllList(shMain);
	}

	@Override
	public int findCountBdcAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainMapper.findCountBdcAllList(shMain);
	}

	@Override
	public List<ShMain> selectQsxxTS(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainMapper.selectQsxxTS(shMain);
	}

	@Override
	public List<QlrTs> selectQlr(QlrTs qlrTs) {
		// TODO Auto-generated method stub
		return shMainMapper.selectQlr(qlrTs);
	}

	@Override
	public List<YwrTs> selectYwr(YwrTs ywrTs) {
		// TODO Auto-generated method stub
		return shMainMapper.selectYwr(ywrTs);
	}

	public List<YwrTs> selectQzh(YwrTs ywrTs) {
		return this.shMainMapper.selectQzh(ywrTs);
	}

}
