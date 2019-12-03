package com.xiaoshu.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.oracle.mapper.BdcsjMapper;
import com.xiaoshu.admin.service.db2.BdcsjService;

@Service
public class BdcsjServiceImpl implements BdcsjService{
	
	@Autowired
	private BdcsjMapper bdcsjMapper;
	
	//不动产查询
	@Override
	public List<Bdcsj> selectBdcsjList(Bdcsj bdcsj) {
		// TODO Auto-generated method stub		
		return bdcsjMapper.selectBdcsjList(bdcsj);
	}
	
	//insert表bdcfile
	@Override
	public boolean insertBdcfile(Bdcfile bdcfile) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdcfile(bdcfile);
	}

	@Override
	public boolean insertBdcquery(Bdcquery bdcquery) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdcquery(bdcquery);
	}

	@Override
	public String selectCountCS(String slbh) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectCountCS(slbh);
	}

	@Override
	public String selectBdcdyh(Bdcsj bdcsj) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectBdcdyh(bdcsj);
	}

	@Override
	public int getByDyzt(String bdcqzh) {
		// TODO Auto-generated method stub
		return bdcsjMapper.getByDyzt(bdcqzh);
	}

	@Override
	public int getByCfzt(String bdcqzh) {
		// TODO Auto-generated method stub
		return bdcsjMapper.getByCfzt(bdcqzh);
	}
	
	@Override
	public boolean insertBdczy(Bdczy bdczy) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdczy(bdczy);
	}

	@Override
	public boolean insertBdcdy(Bdcdy bdcdy) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdcdy(bdcdy);
	}

	@Override
	public List<Bdcsj> selectCf(Bdcsj bdcsj) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectCf(bdcsj);
	}

	@Override
	public List<Bdcsj> selectDy(Bdcsj bdcsj) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectDy(bdcsj);
	}

	@Override
	public String getByYyzt(String bdcqzh) {
		// TODO Auto-generated method stub
		return null;
		//return bdcsjMapper.getByYyzt(bdcqzh);
	}

	@Override
	public String getByYgzt(String bdcqzh) {
		// TODO Auto-generated method stub
		return null;
		//return bdcsjMapper.getByYgzt(bdcqzh);
	}

	@Override
	public boolean insertBdcJtQuery(Fcxx fcxx) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdcJtQuery(fcxx);
	}

	@Override
	public List<Bdcsj> selectQzcx(Bdcsj bdcsj) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectQzcx(bdcsj);
	}

	@Override
	public List<Bdcquery> selectBdcAllListByUser(Bdcquery bdcquery) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectBdcAllListByUser(bdcquery);
	}

	@Override
	public Integer findCountBdcAllListByUser(Bdcquery bdcquery) {
		// TODO Auto-generated method stub
		return bdcsjMapper.findCountBdcAllListByUser(bdcquery);
	}

	@Override
	public Integer findQzcx(Bdcsj bdcsj) {
		// TODO Auto-generated method stub
		return bdcsjMapper.findQzcx(bdcsj);
	}

	@Override
	public List<Bdcquery> selectBdcquery(Bdcquery bdcquery) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectBdcquery(bdcquery);
	}

	@Override
	public List<Bdczy> selectBdcZy(Bdczy bdczy) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectBdcZy(bdczy);
	}

	@Override
	public List<Bdcdy> selectBdcDy(Bdcdy bdcdy) {
		// TODO Auto-generated method stub
		return bdcsjMapper.selectBdcDy(bdcdy);
	}

	@Override
	public List<Fcxx> findAllFcxx(Fcxx fcxx) {
		// TODO Auto-generated method stub
		return bdcsjMapper.findByFcxx(fcxx);
	}

	@Override
	public boolean updateYwzt(Bdcquery Bdcquery) {
		// TODO Auto-generated method stub
		return bdcsjMapper.updateYwzt(Bdcquery);
	}

	@Override
	public boolean insertBdcdhgl(Bdcdhgl bdcdhgl) {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertBdcdhgl(bdcdhgl);
	}

	@Override
	public boolean insertSlbh() {
		// TODO Auto-generated method stub
		return bdcsjMapper.insertSlbh();
	}
}
