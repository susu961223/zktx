package com.xiaoshu.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.FjEntity;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.ShMainService;
import com.xiaoshu.admin.service.db3.ShgxcService;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DatatablesView;
import com.xiaoshu.common.util.DateUtils;
import com.xiaoshu.common.util.DictionaryUtils;
import com.xiaoshu.common.util.FileUtil;

@RestController
@RequestMapping("admin/system/shUpadte")
public class shTsController {
	@Autowired
	private ShMainSwService shMainSwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShMainService shMainService;
	@Autowired
	private ShgxcService shgxcServcie;

	/**
	 * 不动产首页
	 */
	
	@SysLog("在主页面显示原始数据（新）---管理员页面 ")
	@PostMapping("mainTz")
	@ResponseBody
	public String mainTz(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(required = false, name = "starttime") String starttime,
			@RequestParam(required = false, name = "endtime") String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());

		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setStarttime(starttime);
		test.setEndtime(endtime);
/*		
		List<ShMain> data = shMainSwService.selectMain(test);
		List<ShMain> data1 = new ArrayList<>();
		for(ShMain shmain:data) {
			int i = shgxcServcie.findSlbh(shmain);
			if(i>0) {
				shmain.setYwFgzt("0");
				shMainSwService.swUpdatefje(shmain);
			}
			data1.add(shmain);
		}*/
		
		List<ShMain> list = shMainSwService.selectMain(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		// 处理收件状态
		int total = shMainSwService.findMainCount(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	@SysLog("在主页面显示原始数据（新）---不动产页面 ")
	@PostMapping("mainbdcTz")
	@ResponseBody
	public String mainbdcTz(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(required = false, name = "starttime") String starttime,
			@RequestParam(required = false, name = "endtime") String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());

		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setQuerymc(user.getLoginName());
		test.setStarttime(starttime);
		test.setEndtime(endtime);
/*		
		List<ShMain> data = shMainSwService.selectMain(test);
		List<ShMain> data1 = new ArrayList<>();
		for(ShMain shmain:data) {
			int i = shgxcServcie.findSlbh(shmain);
			if(i>0) {
				shmain.setYwFgzt("0");
				shMainSwService.swUpdatefje(shmain);
			}
			data1.add(shmain);
		}*/
		// 处理收件状态
		List<ShMain> list = shMainSwService.selectMain(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		int total = shMainSwService.findMainCount(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	@SysLog("在主页面显示原始数据（新）---税务页面 ")
	@PostMapping("mainswTz")
	@ResponseBody
	public String mainswTz(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh,
			@RequestParam(required = false, name = "starttime") String starttime,
			@RequestParam(required = false, name = "endtime") String endtime) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());

		ShMain test = new ShMain();
		test.setStart(start);
		test.setLength(length);
		test.setYwh(slbh);
		test.setSwblr(user.getLoginName());
		test.setStarttime(starttime);
		test.setEndtime(endtime);
/*		
		List<ShMain> data = shMainSwService.selectMain(test);
		List<ShMain> data1 = new ArrayList<>();
		for(ShMain shmain:data) {
			int i = shgxcServcie.findSlbh(shmain);
			if(i>0) {
				shmain.setYwFgzt("0");
				shMainSwService.swUpdatefje(shmain);
			}
			data1.add(shmain);
		}*/
		List<ShMain> list = shMainSwService.selectMain(test);
		List<ShMain> data = new ArrayList<>();
		for(ShMain shmain:list) {
			shmain.setYwh(StringUtils.toString(shmain.getYwh()));
			shmain.setBdczh(StringUtils.toString(shmain.getBdczh()));
			shmain.setZl(StringUtils.toString(shmain.getZl()));
			shmain.setQuerymc(StringUtils.toString(shmain.getQuerymc()));
			shmain.setQueryrq(StringUtils.toString(shmain.getQueryrq()));
			data.add(shmain);
		}
		// 处理收件状态
		int total = shMainSwService.findMainCount(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
	
	@PostMapping("mainSjd")
	@ResponseBody
	public ModelAndView findAllList(String slbh){
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		ShMain test = new ShMain();
		test.setYwh(slbh);
		//附件信息查询
   		List<FjEntity> fjlist= new ArrayList<>();
   		List<String> fjPath = new ArrayList<>();
		List<String> list = FileUtil.getAllFileName("files/files/"+slbh+"/",fjPath);
   		if(list!=null){
   			for(String path:list){
   				System.out.println(path);
   				FjEntity fj = new FjEntity();
   				fj.setYwh(slbh);
   				fj.setFilePath(path);
   				fjlist.add(fj);
   			}
   		}
   		//权属信息查询
   		List<ShMain> qsxxList1 = shMainService.selectQsxxTS(test);
   		List<ShMain> qsxxList = new ArrayList<>();
   		for(ShMain S:qsxxList1) {
   			S.setFdcjyjg(S.getFdcjyjg()+"万元");
   			String qllx = DictionaryUtils.getQLLX(S.getQllx());
   			S.setQllx(qllx);
   			String fwxz = DictionaryUtils.getFWXZ(S.getFwxz());
   			S.setFwxz(fwxz);
   			String fwjg = DictionaryUtils.getFWJG(S.getFwjg());
   			S.setFwjg(fwjg);
   			qsxxList.add(S);
   		}
   		//权利人信息查询
   		QlrTs qlrTs = new QlrTs();
		qlrTs.setQlrywh(slbh);
		List<QlrTs> qlrList = new ArrayList<>();
		List<QlrTs> qlrList1 = shMainService.selectQlr(qlrTs);
		for(QlrTs Q:qlrList1) {
			String zjzl = DictionaryUtils.getZJZL(Q.getZjzl());
			Q.setZjzl(zjzl);
			String qlrlx = DictionaryUtils.getQLRLX(Q.getQlrlx());
			Q.setQlrlx(qlrlx);
			qlrList.add(Q);
		}
		//义务人信息查询
		YwrTs ywrTs = new YwrTs();
		ywrTs.setYwrywh(slbh);
		List<YwrTs> ywrList = new ArrayList<>();
		List<YwrTs> ywrList1 = shMainService.selectYwr(ywrTs);
		for(YwrTs Y:ywrList1) {
			List<YwrTs> ywrList2 = shMainService.selectQzh(ywrTs);
			for(YwrTs s:ywrList2) {
				Y.setYwrbdcqzh(s.getYwrbdcqzh());
			}
			String zjzl = DictionaryUtils.getZJZL(Y.getYwrzjzl());
			Y.setYwrzjzl(zjzl);
			String qlrlx = DictionaryUtils.getQLRLX(Y.getYwrqlrlx());
			Y.setYwrqlrlx(qlrlx);
			ywrList.add(Y);
		}
		view.addObject("qsxxList", qsxxList);
		view.addObject("qlrList", qlrList);
		view.addObject("ywrList", ywrList);
   		view.addObject("fjlist", fjlist);
		return view;
	}
	/*@RequiresPermissions("sys:shUpadte:bdcwcTs")
	@SysLog("在主页面显示原始数据 ")
	@PostMapping("bdcwcTs")
	@ResponseBody
	public String bdcwcTs(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh) throws ParseException {
		DatatablesView<ShMain> dataTable = new DatatablesView<ShMain>();
		User user = userService.getById(MySysUser.id());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		ShMain test = new ShMain();
		test.setStart(start + 1);
		test.setLength(start + 10);
		test.setYwh(slbh);
		test.setQuerymc(user.getLoginName());
		
		test.setStarttime(sdf.format(new Date()));
		test.setEndtime(sdf1.format(new Date()));
		
		List<ShMain> data = shMainService.selectBdcAllList(test);
		// 处理收件状态
		List<ShMain> data1 = new ArrayList<ShMain>();
		for (ShMain i : data) {
			List<ShMain> zt = shMainSwService.selectZt(i);
			i.setYwzt("已收件");
			i.setYwSwzt("已收件");
			i.setYwFgzt("已收件");
			for (ShMain k : zt) {
				if (k.getYwzt().equals("0")) {
					i.setYwzt("已收件");
				}else
					if (k.getYwzt().equals("1")) {
						i.setYwzt("已提交");
					} else if (k.getYwzt().equals("2")) {
						i.setYwzt("已完成");
					}
					if (k.getYwSwzt().equals("0")) {
						i.setYwSwzt("已收件");
					}else
					if (k.getYwSwzt().equals("1")) {
						i.setYwSwzt("已提交");
					} else if (k.getYwSwzt().equals("2")) {
						i.setYwSwzt("已完成");
					}
					if (k.getYwFgzt().equals("0")) {
						i.setYwFgzt("已收件");
					}else
					if (k.getYwFgzt().equals("1")) {
						i.setYwFgzt("已提交");
					} else if (k.getYwFgzt().equals("2")) {
						i.setYwFgzt("已完成");
					}
			}

			data1.add(i);
		}
		int total = shMainService.findCountBdcAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data1);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;
	}
*/
	/**
	 * 将三河原始库数据保存到独立数据库mysql
	 */

	@PostMapping("/insertMysql")
	public ModelAndView insertMysql(String slbh) {
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		User user = userService.getById(MySysUser.id());
		// 处理日期
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 是否插入成功
		String msg = "";
		// 权属
		ShMain test = new ShMain();
		test.setYwh(slbh);
		int a = shMainSwService.selectYwh(test);

		if (a < 1) {
			List<ShMain> qsxxList = shMainService.selectQsxxTS(test);
			for (ShMain i : qsxxList) {
				i.setYwh(slbh);
				i.setQuerymc(user.getLoginName());
				i.setQueryrq(sdf.format(date));
				i.setYwzt("0");
				i.setZt("0");
				shMainSwService.insertShSl(i);
			}
			// 权利人
			QlrTs testQLlr = new QlrTs();
			testQLlr.setQlrywh(slbh);
			List<QlrTs> qlrList = shMainService.selectQlr(testQLlr);
			for (QlrTs j : qlrList) {
				j.setQlrywh(slbh);
				shMainSwService.insertshQlr(j);
			}
			// 义务人
			YwrTs testYwr = new YwrTs();
			testYwr.setYwrywh(slbh);
			List<YwrTs> ywrList = shMainService.selectYwr(testYwr);
			for (YwrTs k : ywrList) {
				List<YwrTs> ywrList2 = shMainService.selectQzh(testYwr);
				for(YwrTs s:ywrList2) {
					k.setYwrbdcqzh(s.getYwrbdcqzh());
				}
				k.setYwrywh(slbh);
				shMainSwService.insertShYwr(k);
			}
			msg = "数据更新成功";
		}
		view.addObject("msg", msg);
		return view;
	}

	/**
	 * 将不动产状态提交税务
	 */
	@PostMapping("/upDateSwZt")
	public ModelAndView updateSwzt(String slbh,String tsr){
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		ShMain shMain = new ShMain();
		shMain.setYwh(slbh);
		shMain.setYwzt("1");
		shMain.setYwSwzt("0");
		shMain.setSwblr(tsr);
		shMain.setYwFgzt("2");
		boolean msg = shMainSwService.swUpdatefje(shMain);
		view.addObject("msg", msg);
		return view;
	}
	
	@PostMapping("/upDateywZt")
	public ModelAndView updateywzt(String slbh){
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		ShMain shMain = new ShMain();
		shMain.setYwh(slbh);
		shMain.setYwzt("2");
		boolean msg = shMainSwService.swUpdatefje(shMain);
		view.addObject("msg", msg);
		return view;
	}
	/**
	 * 买方税务金额
	 */

	@PostMapping("/updateMfje")
	public ModelAndView swUpdate(String ywh, String mfje,String smfje) {
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		User user = userService.getById(MySysUser.id());
		ShMain shMain = new ShMain();
		shMain.setYwh(ywh);
		shMain.setMfje(mfje);
		shMain.setSmfje(smfje);
		shMain.setYwSwzt("1");
		shMain.setSwblrq(DateUtils.getDate());
		boolean msg = shMainSwService.swUpdateMfje(shMain);
		view.addObject("msg", msg);
		return view;
	}
	
	
/*	@GetMapping("/deleteAll")
	public ModelAndView deleteAll(String slbh) {
		ModelAndView view = new ModelAndView("/admin/deleAll");
		System.out.println(slbh);
		ShMain shMain = new ShMain();
		shMain.setYwh(slbh);
		shMain.setZt("1");
		shMainSwService.swUpdatefje(shMain);
		return view;
	}*/
}
