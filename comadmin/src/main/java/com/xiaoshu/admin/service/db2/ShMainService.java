package com.xiaoshu.admin.service.db2;


import java.util.List;

import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;

public interface ShMainService {
	
	  public abstract List<ShMain> selectBdcAllList(ShMain paramShMain);
	  
	  public abstract int findCountBdcAllList(ShMain paramShMain);
	  
	  public abstract List<ShMain> selectQsxxTS(ShMain paramShMain);
	  
	  public abstract List<QlrTs> selectQlr(QlrTs paramQlrTs);
	  
	  public abstract List<YwrTs> selectYwr(YwrTs paramYwrTs);
	  
	  public abstract List<YwrTs> selectQzh(YwrTs paramYwrTs);
}
