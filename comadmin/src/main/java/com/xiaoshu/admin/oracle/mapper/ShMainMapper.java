package com.xiaoshu.admin.oracle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshu.admin.entity.Menu;
import java.util.List;
import java.util.Map;

import com.xiaoshu.admin.entity.vo.ShowMenuVo;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;

public interface ShMainMapper extends BaseMapper<ShMain> {
	  public abstract List<ShMain> selectBdcAllList(ShMain paramShMain);
	  
	  public abstract int findCountBdcAllList(ShMain paramShMain);
	  
	  public abstract List<ShMain> selectQsxxTS(ShMain paramShMain);
	  
	  public abstract List<QlrTs> selectQlr(QlrTs paramQlrTs);
	  
	  public abstract List<YwrTs> selectYwr(YwrTs paramYwrTs);
	  
	  public abstract List<YwrTs> selectQzh(YwrTs paramYwrTs);
}