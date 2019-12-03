package com.xiaoshu.admin.model;

public class FjEntity {
	
	private String ywh;
	
	private String sjsj;
	
	private String sjlx;
	
	private String sjmc;
	
	private String fileTypeName;
	
	private String filePath;

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getYwh() {
		return ywh;
	}

	public void setYwh(String ywh) {
		this.ywh = ywh;
	}

	public String getSjsj() {
		return sjsj;
	}

	public void setSjsj(String sjsj) {
		this.sjsj = sjsj;
	}

	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}

	public String getSjmc() {
		return sjmc;
	}

	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}
	
    
    public String getSuffix() {
        return filePath.substring(filePath.length() - 3);
    }

}
