package com.xiaoshu.admin.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.oracle.mapper.BdcFileMapper;
import com.xiaoshu.admin.service.db2.BdcFileService;

@Service
public class BdcFileServiceImpl implements BdcFileService{
	
	@Autowired
	private BdcFileMapper bdcFileMapper;

	@Override
	public List<Bdcfile> findAllFileFilenameList(Bdcfile Bdcfile) {
		// TODO Auto-generated method stub
		return bdcFileMapper.findAllFileFilenameList(Bdcfile);
	}

	@Override
	public Bdcfile findAllFilePathList(Bdcfile Bdcfile) {
		// TODO Auto-generated method stub
		return bdcFileMapper.findAllFilePathList(Bdcfile);
	}

	@Override
	public boolean delFj(Bdcfile bdcfile) {
		// TODO Auto-generated method stub
		return bdcFileMapper.delFj(bdcfile);
	}

}
