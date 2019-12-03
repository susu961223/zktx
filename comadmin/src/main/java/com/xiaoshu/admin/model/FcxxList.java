package com.xiaoshu.admin.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FcxxList implements Serializable{
	
	private List<Fcxx> fcxxLst;

	public List<Fcxx> getFcxxLst() {
		return fcxxLst;
	}

	public void setFcxxLst(List<Fcxx> fcxxLst) {
		this.fcxxLst = fcxxLst;
	}

}
