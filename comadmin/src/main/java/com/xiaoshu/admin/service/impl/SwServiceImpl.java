package com.xiaoshu.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.oracle.mapper.SwMapper;
import com.xiaoshu.admin.service.db2.SwService;


@Service
public class SwServiceImpl implements SwService{
	
	@Autowired
	private SwMapper swmapper;

	@Override
	public String selectFileNameList(Bdcfile Bdcfile) {
		// TODO Auto-generated method stub
		return swmapper.selectFileNameList(Bdcfile);
	}

	@Override
	public List<Bdcquery> selectSwList(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.selectSwList(Bdcquery);
	}

	@Override
	public List<Bdcdy> selectDyList(Bdcdy BdcDy) {
		// TODO Auto-generated method stub
		return swmapper.selectDyList(BdcDy);
	}

	@Override
	public boolean upDateJfje(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.upDateJfje(Bdcquery);
	}

	@Override
	public List<Bdcquery> selectSwAllList(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.selectSwAllList(Bdcquery);
	}

	@Override
	public Integer findCountSwAllList(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.findCountSwAllList(Bdcquery);
	}

	@Override
	public List<Bdcquery> selectSwAllListByUser(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.selectSwAllListByUser(Bdcquery);
	}

	@Override
	public Integer findCountSwAllListByUser(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.findCountSwAllListByUser(Bdcquery);
	}

	@Override
	public List<Fcxx> findAllJtxxWinthSlbh(Fcxx Fcxx) {
		// TODO Auto-generated method stub
		return swmapper.findAllJtxxWinthSlbh(Fcxx);
	}

	@Override
	public List<Bdcquery> selectSwListDxs(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return swmapper.selectSwListDxs(Bdcquery);
	}

	@Override
	public List<Fcxx> findAllJtxxWinthSlbh1(Fcxx Fcxx) {
		// TODO Auto-generated method stub
		return swmapper.findAllJtxxWinthSlbh1(Fcxx);
	}

	@Override
	public List<Bdczy> findZydjList(Bdczy Bdczy) {
		// TODO Auto-generated method stub
		return swmapper.findZydjList(Bdczy);
	}

}
