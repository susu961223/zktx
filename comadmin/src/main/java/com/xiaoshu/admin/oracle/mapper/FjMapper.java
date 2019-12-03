package com.xiaoshu.admin.oracle.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xiaoshu.admin.model.*;

public interface FjMapper {
	
	@Select("select * from sj where ywh like #{ywh}")
	List<FjEntity> findFj(FjEntity fjEntity);

}
