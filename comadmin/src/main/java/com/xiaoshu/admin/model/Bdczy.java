package com.xiaoshu.admin.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoshu.common.base.DataEntity;

@TableName("bdc_zy")
public class Bdczy extends DataEntity<Bdczy>{
	private String ywh;
	private String  qlr;
	private String  zjh;
	private String  lxdh;
	private String  zjlb;
	private Date  zyrq;
	public String getYwh() {
		return ywh;
	}
	public void setYwh(String ywh) {
		this.ywh = ywh;
	}
	public String getQlr() {
		return qlr;
	}
	public void setQlr(String qlr) {
		this.qlr = qlr;
	}
	public String getZjh() {
		return zjh;
	}
	public void setZjh(String zjh) {
		this.zjh = zjh;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getZjlb() {
		return zjlb;
	}
	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
	}
	public Date getZyrq() {
		return zyrq;
	}
	public void setZyrq(Date zyrq) {
		this.zyrq = zyrq;
	}
	
}
