package com.xiaoshu.admin.service.db2;

import java.util.List;
import java.util.Map;

import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.DJshare;
import com.xiaoshu.admin.model.Fcxx;

public interface DJshareService {

	List<DJshare> selectDJshare(DJshare dJshare);

	Integer findCountDJshare(DJshare dJshare);

	List<DJshare> selectQsxxList(DJshare dJshare);

	List<DJshare> selectQsxxYwrList(DJshare dJshare);
	
	List<DJshare> selectDJshareAll(DJshare dJshare);

}
