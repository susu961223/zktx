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
@RequestMapping("admin/shfg/res")
public class shfgController {

	@Autowired
	private ShMainSwService shMainSwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShMainService shMainService;
	
	
	@PostMapping("shfgysj")
	@ResponseBody
	public String shfgysj(@RequestParam(name = "start", required = false) Integer start,
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
		test.setYwFgzt("0");
		test.setZl(zl);
		test.setQuerymc(user.getLoginName());
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
		List<ShMain> list = shMainSwService.shbdcfgList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcfgCountAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	@PostMapping("shfgytj")
	@ResponseBody
	public String shfgytj(@RequestParam(name = "start", required = false) Integer start,
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
		test.setYwFgzt("1");
		test.setZl(zl);
		test.setQuerymc(user.getLoginName());
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
		List<ShMain> list = shMainSwService.shbdcfgList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcfgCountAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	

	@PostMapping("shfgywc")
	@ResponseBody
	public String shfgywc(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(name = "zl", required = false) String zl,
			@RequestParam(name = "starttime", required = false) String starttime,
			@RequestParam(name = "endtime", required = false) String endtime) throws ParseException {
		System.out.println(slbh);
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());
		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setYwFgzt("2");
		test.setZl(zl);
		test.setQuerymc(user.getLoginName());
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
		List<ShMain> list = shMainSwService.shbdcfgList(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findbdcfgCountAllList(test);	
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}


}
