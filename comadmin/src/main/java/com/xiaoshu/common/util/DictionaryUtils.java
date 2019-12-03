package com.xiaoshu.common.util;

public class DictionaryUtils {
	
	private static String fwjg;
	
	private static String fwxz;
	
	private static String qllx;
	
	private static String qlrlx;
	
	private static String zjlx;
	
	public static String getFWJG(String fwjgTest) {
		if(fwjgTest.equals("1")) {
			fwjg="钢结构";
		}else if(fwjgTest.equals("2")) {
			fwjg="钢和钢筋混凝土结构";
		}else if(fwjgTest.equals("3")) {
			fwjg="钢筋混凝土结构";
		}else if(fwjgTest.equals("4")) {
			fwjg="混合结构";
		}else if(fwjgTest.equals("5")) {
			fwjg="砖木结构";
		}else if(fwjgTest.equals("6")) {
			fwjg="其它结构";
		}else {
			fwjg=fwjgTest;
		}
		return fwjg;
	}
	
	public static String getFWXZ(String fwxzTest) {
		if(fwxzTest.equals("0")) {
			fwxz="市场化商品房";
		}else if(fwxzTest.equals("1")) {
			fwxz="动迁房";
		}else if(fwxzTest.equals("2")) {
			fwxz="配套商品房";
		}else if(fwxzTest.equals("3")) {
			fwxz="公共租赁住房";
		}else if(fwxzTest.equals("4")) {
			fwxz="廉租住房";
		}else if(fwxzTest.equals("5")) {
			fwxz="限价普通商品住房";
		}else if(fwxzTest.equals("6")) {
			fwxz="经济适用住房";
		}else if(fwxzTest.equals("7")) {
			fwxz="定销商品房";
		}else if(fwxzTest.equals("8")) {
			fwxz="集资建房";
		}else if(fwxzTest.equals("9")) {
			fwxz="福利房";
		}else if(fwxzTest.equals("99")) {
			fwxz="其它";
		}else {
			fwxz=fwxzTest;
		}
		return fwxz;
	}
	
	public static String getQLLX(String qllxTest) {
		if(qllxTest.equals("1")) {
			qllx="集体土地所有权";
		}else if(qllxTest.equals("2")) {
			qllx="国家土地所有权";
		}else if(qllxTest.equals("3")) {
			qllx="国有建设用地使用权";
		}else if(qllxTest.equals("4")) {
			qllx="国有建设用地使用权/房屋（构筑物）所有权";
		}else if(qllxTest.equals("5")) {
			qllx="宅基地使用权";
		}else if(qllxTest.equals("6")) {
			qllx="宅基地使用权/房屋（构筑物）所有权";
		}else if(qllxTest.equals("7")) {
			qllx="集体建设用地使用权";
		}else if(qllxTest.equals("8")) {
			qllx="集体建设用地使用权/房屋（构筑物）所有权";
		}else if(qllxTest.equals("9")) {
			qllx="土地承包经营权";
		}else if(qllxTest.equals("10")) {
			qllx="土地承包经营权/森林、林木所有权";
		}else if(qllxTest.equals("11")) {
			qllx="林地使用权";
		}else if(qllxTest.equals("12")) {
			qllx="林地使用权/森林、林木使用权";
		}else if(qllxTest.equals("13")) {
			qllx="草原使用权";
		}else if(qllxTest.equals("14")) {
			qllx="水域滩涂养殖权";
		}else if(qllxTest.equals("15")) {
			qllx="海域使用权";
		}else if(qllxTest.equals("16")) {
			qllx="海域使用权/构（建）筑物所有权";
		}else if(qllxTest.equals("17")) {
			qllx="无居民海岛使用权";
		}else if(qllxTest.equals("18")) {
			qllx="无居民海岛使用权/构（建）筑物所有权";
		}else if(qllxTest.equals("19")) {
			qllx="地役权";
		}else if(qllxTest.equals("20")) {
			qllx="取水权";
		}else if(qllxTest.equals("21")) {
			qllx="探矿权";
		}else if(qllxTest.equals("22")) {
			qllx="采矿权";
		}else if(qllxTest.equals("99")) {
			qllx="其它权利";
		}else {
			qllx=qllxTest;
		}
		return qllx;
	}
	
	public static String getQLRLX(String qlrlxTest) {
		if(qlrlxTest.equals("1")) {
			qlrlx="个人";
		}else if(qlrlxTest.equals("2")) {
			qlrlx="企业";
		}else if(qlrlxTest.equals("3")) {
			qlrlx="事业单位";
		}else if(qlrlxTest.equals("4")) {
			qlrlx="国家机关";
		}else if(qlrlxTest.equals("99")) {
			qlrlx="其它";
		}else {
			qlrlx=qlrlxTest;
		}
		return qlrlx;
	}
	
	public static String getZJZL(String zjlxTest) {
		if(zjlxTest.equals("1")) {
			zjlx="身份证";
		}else if(zjlxTest.equals("2")) {
			zjlx="港澳台身份证";
		}else if(zjlxTest.equals("3")) {
			zjlx="护照";
		}else if(zjlxTest.equals("4")) {
			zjlx="户口簿";
		}else if(zjlxTest.equals("5")) {
			zjlx="军官证（士兵证）";
		}else if(zjlxTest.equals("6")) {
			zjlx="组织机构代码";
		}else if(zjlxTest.equals("7")) {
			zjlx="营业执照";
		}else if(zjlxTest.equals("99")) {
			zjlx="其它";
		}else {
			zjlx=zjlxTest;
		}
		return zjlx;
	}
}
