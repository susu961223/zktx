package com.xiaoshu.admin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoshu.common.base.DataEntity;

@TableName("bdc_file")
public class Bdcfile extends DataEntity<Bdcfile>{
	
	private static final long serialVersionUID = 1L;
	
	private String binid;  
	private String ywh; 
	private String filename;   
	private Integer filesize;   
	private String  creator;    
	private String  createtime;
	private String  extname;    
	private String  path;
	public String getBinid() {
		return binid;
	}
	public void setBinid(String binid) {
		this.binid = binid;
	}
	public String getYwh() {
		return ywh;
	}
	public void setYwh(String ywh) {
		this.ywh = ywh;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getFilesize() {
		return filesize;
	}
	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getExtname() {
		return extname;
	}
	public void setExtname(String extname) {
		this.extname = extname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
