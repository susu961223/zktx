package com.xiaoshu.admin.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoshu.common.base.DataEntity;


@TableName("bdc_dy")
public class Bdcdy extends DataEntity<Bdcdy>{
	  private String ywh;
	  private String dyr;
	  private String dyje;
	  private String dyrzjh;
	  private Date dykssj;
	  private Date dyjssj;
	  private String dyzjle;
	  private Date zydyrq;
	  private String kssj;
	  private String jssj;
	  
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public Date getDykssj() {
		return dykssj;
	}
	public Date getDyjssj() {
		return dyjssj;
	}

	public Date getZydyrq() {
		return zydyrq;
	}
	public void setZydyrq(Date zydyrq) {
		this.zydyrq = zydyrq;
	}
	public void setDykssj(Date dykssj) {
		this.dykssj = dykssj;
	}
	public void setDyjssj(Date dyjssj) {
		this.dyjssj = dyjssj;
	}
	public String getYwh() {
		return ywh;
	}
	public void setYwh(String ywh) {
		this.ywh = ywh;
	}
	public String getDyr() {
		return dyr;
	}
	public void setDyr(String dyr) {
		this.dyr = dyr;
	}
	public String getDyje() {
		return dyje;
	}
	public void setDyje(String dyje) {
		this.dyje = dyje;
	}
	public String getDyrzjh() {
		return dyrzjh;
	}
	public void setDyrzjh(String dyrzjh) {
		this.dyrzjh = dyrzjh;
	}
	public String getDyzjle() {
		return dyzjle;
	}
	public void setDyzjle(String dyzjle) {
		this.dyzjle = dyzjle;
	}
	  
}
