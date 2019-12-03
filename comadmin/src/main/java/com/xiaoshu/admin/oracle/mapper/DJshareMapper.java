package com.xiaoshu.admin.oracle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.DJshare;
import com.xiaoshu.admin.model.Fcxx;

public interface DJshareMapper extends BaseMapper<DJshare> {

	// 根据当前登录人查询所有数据，分页显示
	List<DJshare> selectDJshare(DJshare dJshare);

	// 根据当前登录人分页显示数据总数
	Integer findCountDJshare(DJshare dJshare);

	List<DJshare> selectQsxxList(DJshare dJshare);

	List<DJshare> selectQsxxYwrList(DJshare dJshare);
	
	List<DJshare> selectDJshareAll(DJshare dJshare);

}
