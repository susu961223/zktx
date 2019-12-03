package com.xiaoshu.admin.shbfk.mapper;

import org.apache.ibatis.annotations.Select;

import com.xiaoshu.admin.model.ShMain;

public interface ShgxcMapper {
	
	@Select("select count(*) from qsdj where ywh=#{ywh}")
	int findSlbh(ShMain shman);
}
