package com.xiaoshu.admin.service;

import java.util.List;

import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;

public interface ShMainSwService {

	List<ShMain> selectSwAllList(ShMain shMain);

	int findCountSwAllList(ShMain shMain);

	int findbdcswCountAllList(ShMain shMain);

	int findbdcfgCountAllList(ShMain shMain);

	List<ShMain> selectZt(ShMain ywh);

	boolean insertShSl(ShMain shMain);

	boolean insertshQlr(QlrTs qlrTs);

	boolean insertShYwr(YwrTs ywrTs);

	int selectYwh(ShMain shMain);

	boolean updatezt(ShMain shMain);

	List<ShMain> selectSwList(ShMain shMain);

	boolean swUpdatefje(ShMain shMain);

	List<ShMain> shbdcAllList(ShMain shMain);

	List<ShMain> shbdcswList(ShMain shMain);

	List<ShMain> shbdcfgList(ShMain shMain);
	
	List<ShMain> selectQsxxTS(ShMain shMain);
	List<QlrTs> selectQlr(QlrTs qlrTs);
	List<YwrTs> selectYwr(YwrTs ywrTs);
	List<ShMain> selectMain(ShMain shMain);
	int findMainCount(ShMain shMain);
	//买房和卖方收税金额
	boolean swUpdateMfje(ShMain shMain);

}
