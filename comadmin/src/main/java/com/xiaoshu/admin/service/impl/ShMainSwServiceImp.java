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
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.db2.BdcFileService;

@Service
public class ShMainSwServiceImp implements ShMainSwService{
	
	@Autowired
	private ShMainSwMapper shMainSwMapper;

	@Override
	public List<ShMain> selectSwAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectSwAllList(shMain);
	}

	@Override
	public int findCountSwAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.findCountSwAllList(shMain);
	}

	@Override
	public List<ShMain>  selectZt(ShMain ywh) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectZt(ywh);
	}
	@Override
	public boolean insertShSl(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.insertShSl(shMain);
	}

	@Override
	public int selectYwh(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectYwh(shMain);
	}

	@Override
	public boolean updatezt(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.updatezt(shMain);
	}

	@Override
	public boolean insertshQlr(QlrTs qlrTs) {
		// TODO Auto-generated method stub
		return shMainSwMapper.insertshQlr(qlrTs);
	}

	@Override
	public boolean insertShYwr(YwrTs ywrTs) {
		// TODO Auto-generated method stub
		return shMainSwMapper.insertShYwr(ywrTs);
	}

	@Override
	public List<ShMain> selectSwList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectSwList(shMain);
	}

	@Override
	public boolean swUpdatefje(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.swUpdatefje(shMain);
	}

	@Override
	public List<ShMain> shbdcAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.shbdcAllList(shMain);
	}

	@Override
	public List<ShMain> shbdcswList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.shbdcswList(shMain);
	}

	@Override
	public List<ShMain> shbdcfgList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.shbdcfgList(shMain);
	}

	@Override
	public int findbdcswCountAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.findbdcswCountAllList(shMain);
	}

	@Override
	public int findbdcfgCountAllList(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.findbdcfgCountAllList(shMain);
	}

	@Override
	public List<ShMain> selectQsxxTS(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectQsxxTS(shMain);
	}

	@Override
	public List<QlrTs> selectQlr(QlrTs qlrTs) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectQlr(qlrTs);
	}

	@Override
	public List<YwrTs> selectYwr(YwrTs ywrTs) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectYwr(ywrTs);
	}

	@Override
	public List<ShMain> selectMain(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.selectMain(shMain);
	}

	@Override
	public int findMainCount(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.findMainCount(shMain);
	}

	@Override
	public boolean swUpdateMfje(ShMain shMain) {
		// TODO Auto-generated method stub
		return shMainSwMapper.swUpdateMfje(shMain);
	}


}
