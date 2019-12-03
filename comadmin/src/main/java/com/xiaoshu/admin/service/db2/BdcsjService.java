package com.xiaoshu.admin.service.db2;

import java.util.List;
import java.util.Map;

import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;

public interface BdcsjService {
	
	String selectCountCS(String slbh);
	
	List<Bdcsj> selectBdcsjList(Bdcsj bdcsj);
	
	List<Bdcsj> selectQzcx(Bdcsj bdcsj);
	
	Integer findQzcx(Bdcsj bdcsj);
	
	String selectBdcdyh(Bdcsj bdcsj);
	
	//抵押
	int getByDyzt(String bdcqzh);
	
	//查封
	int getByCfzt(String bdcqzh);
	
	//异议
	String getByYyzt(String bdcqzh);
	
	//预告
	String getByYgzt(String bdcqzh);
	
	List<Bdcsj> selectCf(Bdcsj bdcsj);
	
	List<Bdcsj> selectDy(Bdcsj bdcsj);
	
	boolean insertBdcquery(Bdcquery bdcquery);
	
	boolean insertSlbh();
	
	boolean insertBdczy(Bdczy bdczy);
	
	boolean insertBdcdy(Bdcdy bdcdy);
	
	boolean insertBdcfile(Bdcfile bdcfile);
	
	boolean insertBdcJtQuery(Fcxx fcxx);
	
	boolean insertBdcdhgl(Bdcdhgl bdcdhgl);
	
	List<Fcxx> findAllFcxx(Fcxx fcxx);
	
	List<Bdcquery> selectBdcAllListByUser(Bdcquery bdcquery);
	
	List<Bdcquery> selectBdcquery(Bdcquery bdcquery);
	
	List<Bdczy> selectBdcZy(Bdczy bdczy);
	
	List<Bdcdy> selectBdcDy(Bdcdy bdcdy);
	
	Integer findCountBdcAllListByUser(Bdcquery bdcquery);
	
	boolean updateYwzt(Bdcquery Bdcquery);
}
