package com.xiaoshu.admin.service.db2;


import java.util.List;

import com.xiaoshu.admin.model.Bdcfile;

public interface BdcFileService {
	
	List<Bdcfile> findAllFileFilenameList(Bdcfile Bdcfile);
	
	Bdcfile findAllFilePathList(Bdcfile Bdcfile);
	
	boolean delFj(Bdcfile bdcfile);

}
