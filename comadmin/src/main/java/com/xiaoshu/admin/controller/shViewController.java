package com.xiaoshu.admin.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.FjEntity;
import com.xiaoshu.admin.model.QlrTs;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.model.YwrTs;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.FjService;
import com.xiaoshu.admin.service.db2.ShMainService;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DictionaryUtils;
import com.xiaoshu.common.util.FileUtil;

@Controller
public class shViewController {

	@Autowired
	private ShMainSwService shMainSwService;
	@Autowired
	private ShMainService shMainService;
	
	/**
	 * 在不动产主页面通过业务号跳转到详情页面
	 * */
	@GetMapping("mainList")
	public String mainList(ModelMap modelMap, String slbh){
		ShMain test = new ShMain();
		test.setYwh(slbh);
		FjEntity fjEntity = new FjEntity();
   		fjEntity.setYwh(slbh);
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
   		modelMap.put("fjlist", fjlist);
   		List<ShMain> qsxxList1 = shMainSwService.selectQsxxTS(test);
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
		List<QlrTs> qlrList1 = shMainSwService.selectQlr(qlrTs);
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
		List<YwrTs> ywrList1 = shMainSwService.selectYwr(ywrTs);
		for(YwrTs Y:ywrList1) {
			String zjzl = DictionaryUtils.getZJZL(Y.getYwrzjzl());
			Y.setYwrzjzl(zjzl);
			String qlrlx = DictionaryUtils.getQLRLX(Y.getYwrqlrlx());
			Y.setYwrqlrlx(qlrlx);
			ywrList.add(Y);
		}
		modelMap.put("qsxxList", qsxxList);
		modelMap.put("qlrList", qlrList);
		modelMap.put("ywrList", ywrList);
		modelMap.put("ywh", slbh);
		return "admin/shUpdate/shQsxx";
	}
	

	@GetMapping("shBwcTs")
	public String bwctz(ModelMap modelMap, String slbh) {
		ShMain test = new ShMain();
		test.setYwh(slbh);
		FjEntity fjEntity = new FjEntity();
   		fjEntity.setYwh(slbh);
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
   		modelMap.put("fjlist", fjlist);
   		
   		
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
		modelMap.put("qsxxList", qsxxList);
		modelMap.put("qlrList", qlrList);
		modelMap.put("ywrList", ywrList);
		modelMap.put("ywh", slbh);
		return "admin/shUpdate/shQsxx";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("fjck")
	public String fjck(ModelMap modelMap,String slbh,String filepath) throws UnsupportedEncodingException{
		filepath = java.net.URLDecoder.decode(filepath,"UTF-8");
		List<FjEntity> fjlist= new ArrayList<>();
		List<String> list = new ArrayList<>();
		List<String> fjPath = FileUtil.getAllFileNames(filepath, list);
   		if(fjPath!=null){
   			for(String path:fjPath){
   				FjEntity fj = new FjEntity();
   				fj.setYwh(slbh);
   				fj.setFileTypeName(path);
   				fj.setFilePath(filepath+"/"+path);
   				fjlist.add(fj);
   			}
   		}
   		modelMap.put("fjlist", fjlist);
   		return "admin/shUpdate/fjck";
	}
	
	@GetMapping("shswQsxx")
	public String shswQsxx(ModelMap modelMap, String slbh) {
		ShMain test = new ShMain();
		test.setYwh(slbh);
		FjEntity fjEntity = new FjEntity();
   		fjEntity.setYwh(slbh);
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
   		modelMap.put("fjlist", fjlist);  
   		List<ShMain> qsxxList1 = shMainSwService.selectQsxxTS(test);
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
		List<QlrTs> qlrList1 = shMainSwService.selectQlr(qlrTs);
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
		List<YwrTs> ywrList1 = shMainSwService.selectYwr(ywrTs);
		for(YwrTs Y:ywrList1) {
			String zjzl = DictionaryUtils.getZJZL(Y.getYwrzjzl());
			Y.setYwrzjzl(zjzl);
			String qlrlx = DictionaryUtils.getQLRLX(Y.getYwrqlrlx());
			Y.setYwrqlrlx(qlrlx);
			ywrList.add(Y);
		}
		modelMap.put("qsxxList", qsxxList);
		modelMap.put("qlrList", qlrList);
		modelMap.put("ywrList", ywrList);
		modelMap.put("ywh", slbh);
		return "admin/shUpdate/shswQsxx";
	}
	
	//房管权属信息查询
	@GetMapping("shfgQsxx")
	public String shfgQsxx(ModelMap modelMap, String slbh) {
		ShMain test = new ShMain();
		test.setYwh(slbh);
		FjEntity fjEntity = new FjEntity();
   		fjEntity.setYwh(slbh);
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
   		modelMap.put("fjlist", fjlist);  
   		List<ShMain> qsxxList1 = shMainSwService.selectQsxxTS(test);
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
		List<QlrTs> qlrList1 = shMainSwService.selectQlr(qlrTs);
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
		List<YwrTs> ywrList1 = shMainSwService.selectYwr(ywrTs);
		for(YwrTs Y:ywrList1) {
			String zjzl = DictionaryUtils.getZJZL(Y.getYwrzjzl());
			Y.setYwrzjzl(zjzl);
			String qlrlx = DictionaryUtils.getQLRLX(Y.getYwrqlrlx());
			Y.setYwrqlrlx(qlrlx);
			ywrList.add(Y);
		}
		modelMap.put("qsxxList", qsxxList);
		modelMap.put("qlrList", qlrList);
		modelMap.put("ywrList", ywrList);
		modelMap.put("ywh", slbh);
		return "admin/shUpdate/shfgQsxx";
	}
	
	@GetMapping("/deleteAll")
	public ModelAndView deleteAll(String slbh) {
		ModelAndView view = new ModelAndView("admin/shbdc/deleAll");
		System.out.println(slbh);
		ShMain shMain = new ShMain();
		shMain.setYwh(slbh);
		shMain.setZt("1");
		shMainSwService.swUpdatefje(shMain);
		return view;
	}
}
