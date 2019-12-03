package com.xiaoshu.admin.oracle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.model.Bdcfile;

public interface BdcFileMapper extends BaseMapper<Bdcfile>{
	
	List<Bdcfile> findAllFileFilenameList(Bdcfile Bdcfile);
	
	Bdcfile findAllFilePathList(Bdcfile Bdcfile);
	
	@Delete("delete from bdc_file where filename=#{filename} and ywh=#{ywh}")
	 boolean delFj(Bdcfile bdcfile);

}
