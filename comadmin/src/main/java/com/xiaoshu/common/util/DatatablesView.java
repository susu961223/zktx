package com.xiaoshu.common.util;

import java.util.List;

public class DatatablesView<T> {
	private List<T> data; //data 与datatales 加载的“dataSrc"对应 
	private int recordsTotal; 
	private int recordsFiltered; 
	private int draw;
	private int length;
	private int start;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	private int PageNum;
	public int getPageNum() {
		return PageNum;
	}
	public void setPageNum(int pageNum) {
		PageNum = pageNum;
	}
	public DatatablesView() { 
	}
	public int getDraw() {
	return draw;
	}
	public void setDraw(int draw) {
	this.draw = draw;
	}
	public List<T> getData() {
	return data;
	}
	public void setData(List<T> data) {
	this.data = data;
	}
	public int getRecordsTotal() {
	return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
	this.recordsTotal = recordsTotal;
	this.recordsFiltered = recordsTotal;
	}
	public int getRecordsFiltered() {
	return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
	this.recordsFiltered = recordsFiltered;
	} 

}
