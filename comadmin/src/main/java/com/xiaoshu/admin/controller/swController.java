package com.xiaoshu.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.BdcFileService;
import com.xiaoshu.admin.service.db2.SwService;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.config.FtpConfigurer;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DatatablesView;
import com.xiaoshu.common.util.DateUtils;

@Controller
@RequestMapping("admin/system/sw")
public class swController {
	
	@Autowired
    public UserService userService;
	
	@Autowired
	public SwService swService;
	
	@Autowired
	public BdcFileService bdcFileService;
	
	@Autowired
	public FtpConfigurer readConfig;
	
	@Autowired
	private ShMainSwService shMainSwService;
	
    @GetMapping("swsj")
    @SysLog("跳转系统税务收件页面")
    public String swsj(){
        return "admin/sw/swsj";
    }
	
    @GetMapping("swwc")
    @SysLog("跳转系统税务我的完成件页面")
    public String swwc(){
        return "admin/sw/swwc";
    }
	
    @GetMapping("fjxx")
    @SysLog("跳转系统税务附件查看页面")
    public String fjxx(ModelMap modelMap,String slbh){
		Bdcfile bdcfile = new Bdcfile();
		bdcfile.setYwh(slbh);
		bdcfile.setPath("申请书");
		List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("身份证明材料");
		List<Bdcfile> filename2 = bdcFileService.findAllFileFilenameList(bdcfile);
		System.out.println("filename2"+filename2.size());
		bdcfile.setYwh(slbh);
		bdcfile.setPath("合同");
		List<Bdcfile> filename3 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("权属证书");
		List<Bdcfile> filename4 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("其他");
		List<Bdcfile> filename5 = bdcFileService.findAllFileFilenameList(bdcfile);
		modelMap.put("listHT1", filename1);
		modelMap.put("listHT2", filename2);
		modelMap.put("listHT3", filename3);
		modelMap.put("listHT4", filename4);
		modelMap.put("listHT5", filename5);
		modelMap.put("slbh", slbh);
        return "admin/sw/fjxx";
    }
    
    @GetMapping("jtxx")
    @SysLog("跳转系统税务家庭信息查看页面")
    public String jtxx(ModelMap modelMap,String slbh){
    	Fcxx fcxx = new Fcxx();
    	fcxx.setBdcywh(slbh);
    	List<Fcxx> list = swService.findAllJtxxWinthSlbh(fcxx);
    	List<Fcxx> list1 = swService.findAllJtxxWinthSlbh1(fcxx);
    	modelMap.put("list",list);
    	modelMap.put("slbh",slbh);
    	modelMap.put("list1",list1);
        return "admin/sw/jtxx";
    }
    
	@SysLog("我的完成件跳转到数据查看页面")
    @GetMapping("swtz")   
    public String swtz(ModelMap modelMap,String slbh){
		Bdcquery Bdcquery = new Bdcquery();
		Bdcdy BdcDy = new Bdcdy();
		Bdcquery.setYwh(slbh);
		BdcDy.setYwh(slbh);
		Bdcfile bdcfile = new Bdcfile();
		bdcfile.setYwh(slbh);
		bdcfile.setPath("申请书");
		List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("身份证明材料");
		List<Bdcfile> filename2 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("合同");
		List<Bdcfile> filename3 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("权属证书");
		List<Bdcfile> filename4 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("其他");
		List<Bdcfile> filename5 = bdcFileService.findAllFileFilenameList(bdcfile);
		List<Bdcquery> list = swService.selectSwList(Bdcquery);
		List<Bdcquery> list1 = swService.selectSwListDxs(Bdcquery);
		List<Bdcdy> bdcdy = swService.selectDyList(BdcDy);
		List<Bdcdy> dyxx = new ArrayList<Bdcdy>();
		if(bdcdy != null){
			for(Bdcdy dysj:bdcdy){
				dysj.setKssj(DateUtils.getDates(dysj.getDykssj()));
				dysj.setJssj( DateUtils.getDates(dysj.getDyjssj()));
				dyxx.add(dysj);
			}
		}
		for(Bdcquery bdcquery:list){
			Bdczy Bdczy = new Bdczy();
			if(bdcquery.getYwlx()!= null && bdcquery.getYwlx().contains("转移")){
				Bdczy.setYwh(bdcquery.getYwh());
				modelMap.put("ZyList", swService.findZydjList(Bdczy));
			}
		}
		modelMap.put("list", list);
		modelMap.put("list1", list1);
		modelMap.put("listHT1", filename1);
		modelMap.put("listHT2", filename2);
		modelMap.put("listHT3", filename3);
		modelMap.put("listHT4", filename4);
		modelMap.put("listHT5", filename5);
		modelMap.put("dyxx", dyxx);
        return "admin/sw/swtz";
    }
    
	@SysLog("跳转系统税务受理页面")
    @GetMapping("swsl")   
    public String swsl(ModelMap modelMap){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		modelMap.put("slbh", sdf.format(date));
        return "admin/sw/swsl";
    }
	
	@SysLog("跳转系统税务不动产打印页面")
    @GetMapping("swbdcdy")   
    public String swbdcdy(String ywh,ModelMap modelMap){
		Bdcquery Bdcquery = new Bdcquery();
		Bdcquery.setYwh(ywh);
		Bdcdy BdcDy = new Bdcdy();
		BdcDy.setYwh(ywh);
		List<Bdcquery> list = swService.selectSwList(Bdcquery);
		List<Bdcquery> list1 = swService.selectSwListDxs(Bdcquery);
		List<Bdcdy> bdcdy = swService.selectDyList(BdcDy);
		List<Bdcdy> dyxx = new ArrayList<Bdcdy>();
		if(bdcdy != null){
			for(Bdcdy dysj:bdcdy){
				dysj.setKssj(DateUtils.getDates(dysj.getDykssj()));
				dysj.setJssj( DateUtils.getDates(dysj.getDyjssj()));
				dyxx.add(dysj);
			}
		}
		for(Bdcquery bdcquery:list){
			Bdczy Bdczy = new Bdczy();
			if(bdcquery.getYwlx().contains("转移")){
				Bdczy.setYwh(bdcquery.getYwh());
				modelMap.put("ZyList", swService.findZydjList(Bdczy));
			}
		}
		modelMap.put("slbh", ywh);
		modelMap.put("list", list);
		modelMap.put("list1", list1);
		modelMap.put("dyxx", dyxx);
        return "admin/sw/swbdcdy";
    }
	
	@SysLog("根据受理编号查询不动产数据")
	@RequestMapping(value = "/findAllList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findAllList(String slbh) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Bdcquery Bdcquery = new Bdcquery();
		Bdcfile bdcfile = new Bdcfile();
		bdcfile.setYwh(slbh);
		bdcfile.setPath("申请书");
		List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
		
		bdcfile.setPath("身份证明材料");
		List<Bdcfile> filename2 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setPath("合同");
		List<Bdcfile> filename3 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setPath("权属证书");
		List<Bdcfile> filename4 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setPath("其他");
		List<Bdcfile> filename5 = bdcFileService.findAllFileFilenameList(bdcfile);
		Bdcdy BdcDy = new Bdcdy();
		Bdcquery.setYwh(slbh);
		BdcDy.setYwh(slbh);
		List<Bdcquery> list = swService.selectSwList(Bdcquery);
		List<Bdcquery> list1 = swService.selectSwListDxs(Bdcquery);
		List<Bdcdy> bdcdy = swService.selectDyList(BdcDy);
		List<Bdcdy> dyxx = new ArrayList<Bdcdy>();
		if(bdcdy != null){
			for(Bdcdy dysj:bdcdy){
				dysj.setKssj(DateUtils.getDates(dysj.getDykssj()));
				dysj.setJssj( DateUtils.getDates(dysj.getDyjssj()));
				dyxx.add(dysj);
			}
		}
		for(Bdcquery bdcquery:list){
			Bdczy Bdczy = new Bdczy();
			if(bdcquery.getYwlx().contains("转移")){
				Bdczy.setYwh(bdcquery.getYwh());
				map.put("ZyList", swService.findZydjList(Bdczy));
			}
		}
		map.put("listHT1", filename1);
		map.put("listHT2", filename2);
		map.put("listHT3", filename3);
		map.put("listHT4", filename4);
		map.put("listHT5", filename5);
		map.put("dyxx", dyxx);
		map.put("list", list);
		map.put("list1", list1);
		return map;
	}
	@SysLog("我的受理件，我的完成跳转不动产数据")
	@ResponseBody
	@RequestMapping(value = "/findSwsl1", method = RequestMethod.GET)
	public ModelAndView findSwsl1(String slbh) throws IOException{
		ModelAndView view = new ModelAndView("admin/sw/swsl");
		Bdcquery Bdcquery = new Bdcquery();
		Bdcdy BdcDy = new Bdcdy();
		Bdcquery.setYwh(slbh);
		BdcDy.setYwh(slbh);
		Bdcfile bdcfile = new Bdcfile();
		bdcfile.setYwh(slbh);
		bdcfile.setPath("申请书");
		List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("身份证明材料");
		List<Bdcfile> filename2 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("合同");
		List<Bdcfile> filename3 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("权属证书");
		List<Bdcfile> filename4 = bdcFileService.findAllFileFilenameList(bdcfile);
		bdcfile.setYwh(slbh);
		bdcfile.setPath("其他");
		List<Bdcfile> filename5 = bdcFileService.findAllFileFilenameList(bdcfile);
		List<Bdcquery> list = swService.selectSwList(Bdcquery);
		List<Bdcquery> list1 = swService.selectSwListDxs(Bdcquery);
		List<Bdcdy> bdcdy = swService.selectDyList(BdcDy);
		List<Bdcdy> dyxx = new ArrayList<Bdcdy>();
		if(bdcdy != null){
			for(Bdcdy dysj:bdcdy){
				dysj.setKssj(DateUtils.getDates(dysj.getDykssj()));
				dysj.setJssj( DateUtils.getDates(dysj.getDyjssj()));
				dyxx.add(dysj);
			}
		}
		view.addObject("list", list);
		view.addObject("list1", list1);
		view.addObject("listHT1", filename1);
		view.addObject("listHT2", filename2);
		view.addObject("listHT3", filename3);
		view.addObject("listHT4", filename4);
		view.addObject("listHT5", filename5);
		view.addObject("dyxx", dyxx);
		return view;
	}
	
	//更新缴费金额
	@ResponseBody
	@RequestMapping(value="upDateJfje",method=RequestMethod.POST)
	public ModelAndView swbc(String slbh,String jfje){
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		User user = userService.getById(MySysUser.id());
		Bdcquery Bdcquery = new Bdcquery();
		Bdcquery.setYwh(slbh);
		Bdcquery.setJfje(jfje);
		Bdcquery.setSwblr(user.getLoginName());
		Bdcquery.setSwblrq(DateUtils.getDate());
		boolean s =  swService.upDateJfje(Bdcquery);
		view.addObject("s", s);
		return view;
	}
	
	
	@SysLog("跳转系统税务我的受理件页面")
	@PostMapping("swsj")
	@ResponseBody
	public String swsj(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh) throws ParseException{
		DatatablesView<Bdcquery> dataTable = new DatatablesView<Bdcquery>();
		Bdcquery test = new Bdcquery();
		test.setStart(start + 1);
		test.setLength(start + 10);
		test.setYwh(slbh);
		List<Bdcquery> data = swService.selectSwAllList(test);
		int total = swService.findCountSwAllList(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;	
    }
	
	@RequiresPermissions("sys:swsj:swwc")
	@SysLog("跳转系统税务我的完成件页面")
	@PostMapping("swwc")
	@ResponseBody
	public String swwc(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh) throws ParseException{
		DatatablesView<Bdcquery> dataTable = new DatatablesView<Bdcquery>();
		User user = userService.getById(MySysUser.id());
		Bdcquery test = new Bdcquery();
		test.setStart(start + 1);
		test.setLength(start + 10);
		test.setYwh(slbh);
		test.setSwblr(user.getLoginName());
		List<Bdcquery> data = swService.selectSwAllListByUser(test);
		int total = swService.findCountSwAllListByUser(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;	
    }
	
}
