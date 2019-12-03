package com.xiaoshu.admin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoshu.common.base.DataEntity;

@TableName("Bdcdhgl")
public class Bdcdhgl extends DataEntity<Bdcdhgl>{
	private String bdczh;
	private String bdcdyh;
	private String zl;
	private String yt;
	private String mj;
	public String getBdczh() {
		return bdczh;
	}
	public void setBdczh(String bdczh) {
		this.bdczh = bdczh;
	}
	public String getBdcdyh() {
		return bdcdyh;
	}
	public void setBdcdyh(String bdcdyh) {
		this.bdcdyh = bdcdyh;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getYt() {
		return yt;
	}
	public void setYt(String yt) {
		this.yt = yt;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
	}

}
