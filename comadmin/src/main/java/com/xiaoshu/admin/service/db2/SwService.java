package com.xiaoshu.admin.service.db2;

import java.util.List;

import com.xiaoshu.admin.model.*;
public interface SwService {
	
	List<Bdcquery> selectSwList(Bdcquery Bdcquery);
	
	List<Bdcquery> selectSwListDxs(Bdcquery Bdcquery);
	
	String selectFileNameList(Bdcfile Bdcfile);
	
	List<Bdcdy> selectDyList(Bdcdy BdcDy);
	
	boolean upDateJfje(Bdcquery Bdcquery);
	
	List<Bdcquery> selectSwAllList(Bdcquery Bdcquery);
	
	Integer findCountSwAllList(Bdcquery Bdcquery);
	
	List<Bdcquery> selectSwAllListByUser(Bdcquery Bdcquery);
	
	Integer findCountSwAllListByUser(Bdcquery Bdcquery);
	
	List<Fcxx> findAllJtxxWinthSlbh(Fcxx Fcxx);
	
	List<Fcxx> findAllJtxxWinthSlbh1(Fcxx Fcxx);
	
	List<Bdczy> findZydjList(Bdczy Bdczy);

}
