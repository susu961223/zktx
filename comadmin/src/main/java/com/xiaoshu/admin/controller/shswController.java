package com.xiaoshu.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.ShMainService;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DatatablesView;

@RestController
@RequestMapping("admin/shsw/res")
public class shswController {
	
	@Autowired
	private ShMainSwService shMainSwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShMainService shMainService;
	
	@PostMapping("shswysj")
	@ResponseBody
	public String shswysj(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(name = "zl", required = false) String zl,
			@RequestParam(name = "starttime", required = false) String starttime,
			@RequestParam(name = "endtime", required = false) String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());
		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setYwSwzt("0");
		test.setZl(zl);
		test.setSwblr(user.getLoginName());
		if(starttime!=null && starttime.length()!=0) {
			starttime = starttime+" 00:00:00";
			System.out.println(starttime);
			test.setStarttime(starttime);
		}
		if(endtime!=null && endtime.length()!=0) {
			endtime = endtime+" 23:59:59";
			System.out.println(endtime);
			test.setStarttime(endtime);
		}
		List<ShMain> list = shMainSwService.shbdcswList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcswCountAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	@PostMapping("shswytj")
	@ResponseBody
	public String shbdcytj(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(name = "zl", required = false) String zl,
			@RequestParam(name = "starttime", required = false) String starttime,
			@RequestParam(name = "endtime", required = false) String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());
		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setYwSwzt("1");
		test.setZl(zl);
		test.setSwblr(user.getLoginName());
		if(starttime!=null && starttime.length()!=0) {
			starttime = starttime+" 00:00:00";
			System.out.println(starttime);
			test.setStarttime(starttime);
		}
		if(endtime!=null && endtime.length()!=0) {
			endtime = endtime+" 23:59:59";
			System.out.println(endtime);
			test.setStarttime(endtime);
		}
		List<ShMain> list = shMainSwService.shbdcswList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcswCountAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	

	@PostMapping("shswywc")
	@ResponseBody
	public String shswbywc(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(name = "zl", required = false) String zl,
			@RequestParam(name = "starttime", required = false) String starttime,
			@RequestParam(name = "endtime", required = false) String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());
		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setYwSwzt("1");
		test.setZl(zl);
		test.setSwblr(user.getLoginName());
		if(starttime!=null && starttime.length()!=0) {
			starttime = starttime+" 00:00:00";
			System.out.println(starttime);
			test.setStarttime(starttime);
		}
		if(endtime!=null && endtime.length()!=0) {
			endtime = endtime+" 23:59:59";
			test.setStarttime(endtime);
		}
		List<ShMain> list = shMainSwService.shbdcswList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcswCountAllList(test);	
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	
	

}
