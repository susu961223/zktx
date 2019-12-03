package com.xiaoshu.admin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.xiaoshu.common.util.DatatablesView;
import com.xiaoshu.common.util.DateUtils;
import com.xiaoshu.common.util.FtpHandler;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcdhgl;
import com.xiaoshu.admin.model.Bdcdy;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.Bdczy;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.model.ShMain;
import com.xiaoshu.admin.service.ShMainSwService;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.BdcFileService;
import com.xiaoshu.admin.service.db2.BdcsjService;
import com.xiaoshu.admin.service.db2.ShMainService;
import com.xiaoshu.admin.service.db2.SwService;
import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.config.FtpConfigurer;
import com.xiaoshu.common.config.MySysUser;

@Controller
@RequestMapping("admin/system/bdc")
public class bdcController {
	
	@Autowired
	BdcsjService bdcsjService;
	
	@Autowired
	public SwService swService;
	
	@Autowired
	public BdcFileService bdcFileService;
	
	@Autowired
    UserService userService;
	
	@Autowired
	private FtpConfigurer readConfig;
	
	@Autowired
	private ShMainService shMainService;
	
	@Autowired
	private ShMainSwService shMainsWService;
	
	/**
	 * 抵押信息
	 * */
	@GetMapping(value="dyxx")
	public String dyxx(String bdczh,ModelMap modelMap) throws UnsupportedEncodingException{
		bdczh = java.net.URLDecoder.decode(bdczh,"UTF-8"); 
		Bdcsj bdcsj = new Bdcsj();
		bdcsj.setBdcqzh(bdczh);
		List<Bdcsj> list = bdcsjService.selectDy(bdcsj);
		List<Bdcsj> list1 = new ArrayList();
		if(list!=null){
			for(Bdcsj i:list){
				if(i.getZwlxqssj()==null){
					i.setZwlxqssj(" ");
				}
				if(i.getZwlxjssj()==null){
					i.setZwlxjssj(" ");
				}
				list1.add(i);
			}
		}
		System.out.println(bdczh+"抵押信息"+list1.size());
		modelMap.put("bdczh", bdczh);
		modelMap.put("list", list1);
		return "admin/bdc/dyxx";
	}
	/**
	 * 查封信息
	 * */
	@GetMapping(value="cfxx")
	public String cfxx(String bdczh,ModelMap modelMap) throws UnsupportedEncodingException{
		bdczh = java.net.URLDecoder.decode(bdczh,"UTF-8"); 
		Bdcsj bdcsj = new Bdcsj();
		bdcsj.setBdcqzh(bdczh);
		List<Bdcsj> list = bdcsjService.selectCf(bdcsj);
		List<Bdcsj> list1 = new ArrayList();
		if(list!=null){
			for(Bdcsj i:list){
				if(i.getCfjssj()==null){
					i.setCfjssj(" ");
				}
				if(i.getCfqssj()==null){
					i.setCfqssj(" ");
				}
				list1.add(i);
			}
		}
		modelMap.put("bdczh", bdczh);
		modelMap.put("list", list1);
		return "admin/bdc/cfxx";
	}
	
   /* @GetMapping(value = "bdcsj")
    public String bdcsj(ModelMap modelMap){
        modelMap.put("bdccxUser",userService.getById(MySysUser.id()));          
     // 获取受理编号，收件时间，收件人
     		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     		Date date = new Date();
     		String i = bdcsjService.selectCountCS(sdf.format(date) + "%");
     		String slbh="";
     		String str ="";
     		if(i==null){
     			str = String.format("%05d", 1);
     			slbh = sdf.format(date) + str;
     		}else{
     			Long k = Long.valueOf(i);
     			slbh = String.valueOf(k+1);
     		}
       modelMap.put("ywh",slbh);
     //连接FTP
   	try {
   		FtpHandler f = readConfig.ftpHandler();
			String ftpUrl="ftp://"+f.ftpUrl();
			modelMap.put("ftpUrl",ftpUrl);
   	}catch (Exception e) {
			// TODO: handle exception
   		
		}	
        return "admin/bdc/bdcsj";
    } */
	@GetMapping(value = "bdcsj")
    public String bdcsj(ModelMap modelMap){
        modelMap.put("bdccxUser",userService.getById(MySysUser.id()));      
        //插入受理编号
        bdcsjService.insertSlbh();
     // 获取受理编号，收件时间，收件人        
     		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     		Date date = new Date();
     		String i = bdcsjService.selectCountCS(sdf.format(date) + "%");
     	 modelMap.put("ywh",i);   
     //连接FTP
   	try {
   		FtpHandler f = readConfig.ftpHandler();
   		String ftpUrl="ftp://"+f.ftpUrl();
		String interUsername=f.interUsername();
		String interPassword=f.interPassword();
		int interPort=f.interPort();
		modelMap.put("ftpUrl",ftpUrl);
		modelMap.put("interUsername",interUsername);
		modelMap.put("interPassword",interPassword);
		modelMap.put("interPort",interPort);
   	}catch (Exception e) {
			// TODO: handle exception
		}	
        return "admin/bdc/bdcsj";
    }
    /**
     * 通过权证号查询不动产的数据
     * */
    @PostMapping("bdcQzcx")
    @ResponseBody
    public String bdcQzcx(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "qzh", required = false) String qzh) throws ParseException{
		DatatablesView<Bdcsj> dataTable = new DatatablesView<Bdcsj>();		
		Bdcsj bdcsj = new Bdcsj();
		bdcsj.setStart(start + 1);
		bdcsj.setLength(start + 10);
    	bdcsj.setBdcqzh(qzh);
    	List<Bdcsj> bdcqzcx=bdcsjService.selectQzcx(bdcsj);
    	int total = bdcsjService.findQzcx(bdcsj);		
		dataTable.setDraw(draw);
		dataTable.setData(bdcqzcx);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;	
    }
    /**
     * 通过权证号查询不动产的数据
     * */
    @PostMapping("bdcsj")
    @ResponseBody
    public ModelAndView bdcsj(String bdcqzh , HttpServletRequest request) {
    	ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
    	Bdcsj bdcsj = new Bdcsj();
    	bdcsj.setBdcqzh(bdcqzh);
    	List<Bdcsj> bdcsjcx=bdcsjService.selectBdcsjList(bdcsj);
    	view.addObject("bdcxjcx", bdcsjcx);
    //查询是否有抵押状态
    	int dyzt = bdcsjService.getByDyzt(bdcqzh);
    	System.out.println(bdcqzh+"通过权证号查询不动产的数据==="+dyzt);
    	if(dyzt>0) { 
    		view.addObject("dyzt", dyzt);
    		List<Bdcsj> bdcsjcxDy=bdcsjService.selectDy(bdcsj);
    		System.out.println(bdcqzh+"通过权证号查询不动产的数据==="+bdcsjcxDy.size());
    		view.addObject("bdcsjcxDy", bdcsjcxDy);
    	}
    //查询是否有查封状态
    	int cfzt=bdcsjService.getByCfzt(bdcqzh);
    	if(cfzt>0) {
    		view.addObject("cfzt", cfzt);
    		List<Bdcsj> bdcsjcxCf=bdcsjService.selectCf(bdcsj);
    		view.addObject("bdcsjcxCf", bdcsjcxCf);
    	}
    //查询是否有异议登记
    	String yyzt=bdcsjService.getByYyzt(bdcqzh);
    	if(yyzt!=null) {
    		view.addObject("yyzt", yyzt);
    	}
    //查询是否有预告登记	
    	String ygzt=bdcsjService.getByYgzt(bdcqzh);
    	if(ygzt!=null) {
    		view.addObject("ygzt", ygzt);
    	}
    	/* for(Bdcsj i:bdcsjcx)
	    {
	    	System.out.println("规划用途====="+i.getQlrmc());
	    }*/    			
    	return view;
    } 
    /**
     * 调用不动产信息预览界面
     * */
    @GetMapping("bdcsjDyYl")
    public String bdcsjDyYl(HttpServletRequest request,ModelMap modelMap) throws UnsupportedEncodingException{
    	String qzh = request.getParameter("qzh");
    	String slbh = request.getParameter("slbh");
    	String sjry = request.getParameter("sjry");
    	sjry = java.net.URLDecoder.decode(sjry,"UTF-8");    	
    	modelMap.put("ywh",slbh);
    	modelMap.put("sjry",sjry);
    	modelMap.put("qzh",qzh);
    	
    	return "admin/bdc/bdcsjDyYl";
    }
    /**
     * 预览权证号查询不动产的数据
     * */
    @PostMapping("bdcsjDyYl")
    @ResponseBody
    public ModelAndView bdcsjDyYl(String bdcqzh , HttpServletRequest request) {
    	ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
    	List<Bdcsj> bdcsjcx=new ArrayList<Bdcsj>();
    	Bdcsj bdcsj = new Bdcsj();
    	bdcsj.setBdcqzh(bdcqzh);
    	bdcsjcx=bdcsjService.selectBdcsjList(bdcsj);   
    //查询是否有抵押状态
    	int dyzt = bdcsjService.getByDyzt(bdcqzh);
    	if(dyzt>0) { 
    		view.addObject("dyzt", dyzt);
    		List<Bdcsj> bdcsjcxDy=new ArrayList<Bdcsj>();
    		bdcsjcxDy=bdcsjService.selectDy(bdcsj);
    		view.addObject("bdcsjcxDy", bdcsjcxDy);
    	}
    //查询是否有查封状态
    	int cfzt=bdcsjService.getByCfzt(bdcqzh);
    	if(cfzt>0) {
    		view.addObject("cfzt", cfzt);
    		List<Bdcsj> bdcsjcxCf=new ArrayList<Bdcsj>();
    		bdcsjcxCf=bdcsjService.selectCf(bdcsj);
    		view.addObject("bdcsjcxCf", bdcsjcxCf);
    	} 
    	view.addObject("bdcxjcx", bdcsjcx);
    	return view;
    }
    /**
     * 调转到我的完成件
     * */
    @GetMapping("bdcwc")
    public String bdcwc(){
        return "admin/bdc/bdcwc";
    }
	/**
	 * 跳转到我的完成件
	 * */
    @RequiresPermissions("sys:bdcsj:bdcwc")
	@PostMapping("bdcwc")
	@ResponseBody
	public String bdcwc(@RequestParam(name = "start", required = false) Integer start,
			@RequestParam(name = "length", required = false) Integer length,
			@RequestParam(name = "draw", required = false) Integer draw,
			@RequestParam(name = "slbh", required = false) String slbh) throws ParseException{
		DatatablesView<Bdcquery> dataTable = new DatatablesView<Bdcquery>();
		User user = userService.getById(MySysUser.id());
		Bdcquery test = new Bdcquery();
		test.setStart(start + 1);
		test.setLength(start + 10);
		test.setYwh(slbh);
		test.setQuerymc(user.getLoginName());
		System.out.println("登录人===="+user.getLoginName());
		List<Bdcquery> data = bdcsjService.selectBdcAllListByUser(test);
		int total = bdcsjService.findCountBdcAllListByUser(test);
		dataTable.setDraw(draw);
		dataTable.setData(data);
		dataTable.setRecordsTotal(total);
		dataTable.setLength(length);
		String data2 = JSON.toJSONString(dataTable);
		return data2;	
    }
   
    /**
     * 通过受理编号跳转到不动产收件页面
     * */
    @GetMapping("bwctz")
    public String bwctz(ModelMap modelMap,String slbh) {
    	Bdcquery bdcQuery=new Bdcquery();
    	bdcQuery.setYwh(slbh);
    	List<Bdcquery>  bwctzcx=bdcsjService.selectBdcquery(bdcQuery);
    	Bdczy bdczy=new Bdczy();
    	bdczy.setYwh(slbh);
    	List<Bdczy>  bwctzcxZy=bdcsjService.selectBdcZy(bdczy);
    	Bdcdy bdcDy=new Bdcdy();
    	bdcDy.setYwh(slbh);
    	List<Bdcdy>  bwctzcxDy=bdcsjService.selectBdcDy(bdcDy);
    	modelMap.put("bwctzcxZy", bwctzcxZy);
    	modelMap.put("bwctzcxDy", bwctzcxDy);
    	modelMap.put("bwctzcx", bwctzcx);
    	return "admin/bdc/bwctz";
    }
    /**
     * 在完成界面跳转到打印预览
     * */
    @GetMapping("bdcwcDyYl")   
    public String bdcwcDyYl(String slbh,ModelMap modelMap){
    	Bdcquery bdcQuery=new Bdcquery();
    	bdcQuery.setYwh(slbh);
    	List<Bdcquery>  bwctzcx=bdcsjService.selectBdcquery(bdcQuery);
    	Bdczy bdczy=new Bdczy();
    	bdczy.setYwh(slbh);
    	List<Bdczy>  bwctzcxZy=bdcsjService.selectBdcZy(bdczy);
    	Bdcdy bdcDy=new Bdcdy();
    	bdcDy.setYwh(slbh);
    	List<Bdcdy>  bwctzcxDy=bdcsjService.selectBdcDy(bdcDy);
    	modelMap.put("bwctzcxZy", bwctzcxZy);
    	modelMap.put("bwctzcxDy", bwctzcxDy);
    	modelMap.put("bwctzcx", bwctzcx);
		modelMap.put("slbh", slbh);
        return "admin/bdc/bdcwcDyYl";
    }
    /**
     * 在完成界面跳转到家庭查询预览
     * */
    @GetMapping("bjtxx")
    public String jtxx(ModelMap modelMap,String slbh){
    	Fcxx fcxx = new Fcxx();
    	fcxx.setBdcywh(slbh);
    	List<Fcxx> list = swService.findAllJtxxWinthSlbh(fcxx);
    	modelMap.put("list",list);
    	modelMap.put("slbh",slbh);
        return "admin/sw/jtxx";
    }
    /**
     * 调用不动产家庭查询界面
     * **/
    @GetMapping("bdcjtcx")
    public String bdcjtc(HttpServletRequest request,ModelMap modelMap){
      	String slbh = request.getParameter("slbh");
    	modelMap.put("slbh",slbh);
        return "admin/bdc/bdcjtcx";
    }
    /**
     * 家庭查询通过权利人名称和证件号码
     * **/
    @PostMapping("bdcjtcs")
    @ResponseBody
    public ModelAndView bdcjtc(@RequestBody JSONObject  bdcjtlst,HttpServletRequest request) {
    	ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
    	//将查询条件封装到map中用于mybtias循环查询
    	List<Fcxx> bdcjtList1 = new ArrayList<Fcxx>();
    	List<Fcxx> bdcjtList = new ArrayList<Fcxx>();
    	List<Fcxx> flogs=new ArrayList<Fcxx>();
    	// 解析JSON获取bdclst		
    		String data=bdcjtlst.toJSONString();
    	 //解析json数据
    	    JSONObject json = JSON.parseObject(data);
    	    String bdcJTcxLst=json.getString("bdcjtLst");
    	    JSONArray bdcjTcxLstArray=JSONArray.parseArray(bdcJTcxLst);
    	    for(int i=0;i<bdcjTcxLstArray.size();i++){
    	    	  String qlrmc=JSONObject.parseObject(JSONObject.toJSONString(bdcjTcxLstArray.get(i))).getString("qlrmc");
                  String zjhm=JSONObject.parseObject(JSONObject.toJSONString(bdcjTcxLstArray.get(i))).getString("zjhm");
                  String zjhmf="";
                  if(zjhm.length()>15) {
                	  zjhmf=zjhm.substring(0, 6)+zjhm.substring(8,zjhm.length());
                  }
                 Fcxx flog=new Fcxx();
 				flog.setQlrmc(qlrmc);
 				flog.setZjhm(zjhm);
 				flog.setZjhmf(zjhmf);
 				flogs.add(flog);
    	    }
    	    for(Fcxx fcxx:flogs) {    	    	
    	    	bdcjtList1=bdcsjService.findAllFcxx(fcxx);
    	    	for(Fcxx fxx:bdcjtList1) {
    	    		bdcjtList.add(fxx);
    			}
    	    }
    	    view.addObject("bdcjtList", bdcjtList);
    	return view;
    }
    /**
     * 调用预览界面不动产家庭查询
     * **/
    @GetMapping("bdcjtcxDyYl")
    public String bdcjtcxDyYl(ModelMap modelMap,String slbh,String qlrmc,String zjhm,String qlrlx) throws UnsupportedEncodingException{
    	qlrmc = java.net.URLDecoder.decode(qlrmc,"UTF-8");	
    	qlrlx = java.net.URLDecoder.decode(qlrlx,"UTF-8");
    	List<Fcxx> flogs=new ArrayList<Fcxx>();
		List<Fcxx> bdcjtList1 = new ArrayList<Fcxx>(); 
		List<Fcxx> bdcjtList = new ArrayList<Fcxx>(); 	
    	if(qlrmc.contains("，")){
    		qlrmc=qlrmc.replaceAll("，", ",");
    	}
    	if(zjhm.contains("，")){
    		zjhm=zjhm.replaceAll("，", ",");
    	}
    	String[] qlr=qlrmc.split(",");
    	String[] zjh=zjhm.split(",");    		
	    	for(int i=0;i<qlr.length;i++) {	
	    		Fcxx flog=new Fcxx();
	    		flog.setQlrmc(qlr[i]); 
	    		String zj="";
	    		String zjhmf="";    		
	    		for(int j=0;j<=i;j++) {
	    			zj=zjh[j];
	    			if(zjhm.length()>15) {
	                	  zjhmf=zjhm.substring(0, 6)+zjhm.substring(8,zjhm.length());
	                  }
		    	}
	    		flog.setZjhm(zj);
	    		flog.setZjhmf(zjhmf);
	    		flogs.add(flog);
	    	}    	
	    	
	    	
    	
    	//将权利人和证件号封装查询
	    for(Fcxx fcxx:flogs) {
	    	bdcjtList1=bdcsjService.findAllFcxx(fcxx);
	    	for(Fcxx fxx:bdcjtList1) {
	    		bdcjtList.add(fxx);
			}
	    }  
	    modelMap.put("slbh", slbh);
	    modelMap.put("qlrmc", qlrmc);
	    modelMap.put("zjhm", zjhm);
	    modelMap.put("bdcjtList", bdcjtList);
	    modelMap.put("qlrlx", qlrlx);
		return "admin/bdc/bdcjtcxDyYl";
    }
    /**
     * 保存家庭查询出来的数据到三河数据库里面
     * */
    @PostMapping("bdcJtInsert")
    @ResponseBody 
	public ModelAndView bdcJtInsert(@RequestBody JSONObject  bdclst,HttpServletRequest request) throws ParseException {
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		//处理日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//是否插入成功
		boolean cs = false;		
		// 解析JSON获取bdclst		
		String data=bdclst.toJSONString();
    	//解析json数据
    	JSONObject json = JSON.parseObject(data);
    	String bdccxLst=json.getString("fcxxLst");
    	JSONArray bdccxLstArray=JSONArray.parseArray(bdccxLst);
    //获取受理编号	
    	String slbh=json.getString("slbh");
    	//获取权利人类型
    	String qlrlx=json.getString("qlrlx");
    	for(int i=0;i<bdccxLstArray.size();i++){
    		//当前业务号    	
              String qlrmc=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(i))).getString("qlrmc");
              String zjhm=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(i))).getString("zjhm");
              String bdczh=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(i))).getString("bdczh");
             String zl=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(i))).getString("zl");
              String yt=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(i))).getString("yt");
            //创建Fcxx
              Fcxx fcxx = new Fcxx();
             // fcxx.setBdcdyh(bdcdyh);
              fcxx.setBdczh(bdczh);
             // fcxx.setFzrq(fzrq);
              fcxx.setGhyt(yt);
              fcxx.setQlrmc(qlrmc);
             // fcxx.setSlbh(ywh);
              fcxx.setZjhm(zjhm);
              fcxx.setZl(zl);
              fcxx.setBdcywh(slbh);
              fcxx.setQlrlx(qlrlx);
              cs = bdcsjService.insertBdcJtQuery(fcxx); 
         }
		view.addObject("cs", cs);
		return view;
	} 
  
    /**
     * 保存通过权证查询出来的数据到三河数据库中
     * */
    @PostMapping("bdcInsert")
    @ResponseBody 
	public ModelAndView bdcInsert(@RequestBody JSONObject  bdclst,HttpServletRequest request) throws ParseException {
		ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
		//处理日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//是否插入成功
		boolean cs = false;		
		// 解析JSON获取bdclst		
		String data=bdclst.toJSONString();
    	//解析json数据
    	JSONObject json = JSON.parseObject(data);
    	String bdccxLst=json.getString("BdccxLst");
    	JSONArray bdccxLstArray=JSONArray.parseArray(bdccxLst);
    //转移登记json	
    	String zyLst=json.getString("ZyLst");
    	JSONArray zyLstArray=JSONArray.parseArray(zyLst);
    //转移及抵押登记json	
    	String zydyLst=json.getString("ZydyLst");
    	JSONArray zydyLstArray=JSONArray.parseArray(zydyLst);
    	//首次登记
    
    		
    		//当前业务号
    		  String ywh=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("ywh");
              String qlr=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("qlr");
              String zjh=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("zjh");
              String bdczh=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("bdczh");
            //当前收件时间（系统时间）  
              String sjsj=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("sjsj");
            //当前收件人员（登陆人员名称）  
              String sjry=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("sjry");
              String ywlx=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("ywlx");
              String dyzt=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("dyzt");
              String cfzt=JSONObject.parseObject(JSONObject.toJSONString(bdccxLstArray.get(0))).getString("cfzt");
              //创建Bdcquery
              Bdcquery bdcquery = new Bdcquery();
              bdcquery.setYwh(ywh);
              bdcquery.setQlr(qlr);
              bdcquery.setZjh(zjh);
              bdcquery.setBdczh(bdczh);
              bdcquery.setQueryrq(sjsj);
              bdcquery.setQuerymc(sjry);
              bdcquery.setYwlx(ywlx);
              bdcquery.setDyzt(dyzt);
              bdcquery.setCfzt(cfzt);
              bdcquery.setYwzt("1");
              cs = bdcsjService.insertBdcquery(bdcquery);  
              
            ///查询多户数据保存到bdc_dhgl表中 
             List<Bdcsj> bdcsjcx=new ArrayList<Bdcsj>();
          	Bdcsj bdcsj = new Bdcsj();
          	bdcsj.setBdcqzh(bdczh);
          	bdcsjcx=bdcsjService.selectBdcsjList(bdcsj);  
          	System.out.println("bdcsjcx===================================="+bdcsjcx.size());
          	Bdcdhgl bdcdhgl=new Bdcdhgl();
          	for(Bdcsj k:bdcsjcx) {  
          		System.out.println("k.getBdcdyh()====================="+k.getBdcdyh());
          		bdcdhgl.setBdcdyh(k.getBdcdyh());
          		bdcdhgl.setBdczh(bdczh);
          		bdcdhgl.setZl(k.getZl());
          		bdcdhgl.setMj(k.getFwmj());
          		bdcdhgl.setYt(k.getGhyt());
          		cs =bdcsjService.insertBdcdhgl(bdcdhgl); 
          	}              
   	    
             //转移Bdcdy
               if(ywlx.equals("转移")) { 
            	   for(int j=0;j<zyLstArray.size();j++){            	   
	            	   String zyqlr=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(j))).getString("zyqlr");
	            	   String zyzjh=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(j))).getString("zyzjh");
	            	   String lxdh=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(j))).getString("lxdh");
	            	   String zjlb=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(j))).getString("zjlb");
	            	 //创建Bdczy（转移）
	            	   Bdczy bdczy = new Bdczy();
	            	   bdczy.setYwh(ywh);
	            	   bdczy.setQlr(zyqlr);
	            	   bdczy.setZjh(zyzjh);
	            	   bdczy.setLxdh(lxdh);
	            	   bdczy.setZjlb(zjlb);  
	            	   bdczy.setZyrq(sdf.parse(sjsj));
	            	 cs = bdcsjService.insertBdczy(bdczy);
            	   }
               }
           //转移及抵押Bdcdy
               if(ywlx.equals("转移及抵押")) {
            	   for(int k=0;k<zyLstArray.size();k++){ 
	            	   String zyqlr=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(k))).getString("dyqlr");
	            	   String zyzjh=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(k))).getString("dyzjh");
	            	   String lxdh=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(k))).getString("dylxdh");
	            	   String zjlb=JSONObject.parseObject(JSONObject.toJSONString(zyLstArray.get(k))).getString("dyzjlb");            	   
	            	   //创建Bdczy（转移）
	            	   Bdczy bdczy = new Bdczy();
	            	   bdczy.setYwh(ywh);
	            	   bdczy.setQlr(zyqlr);
	            	   bdczy.setZjh(zyzjh);
	            	   bdczy.setLxdh(lxdh);
	            	   bdczy.setZjlb(zjlb); 
	            	   bdczy.setZyrq(sdf.parse(sjsj));
	            	  cs = bdcsjService.insertBdczy(bdczy); 
            	   }
            	   for(int g=0;g<zydyLstArray.size();g++){ 
	            	   String dyr=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dyr");
	            	   String dyje=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dyje");
	            	   String dyrzjh=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dyrzjh");
	            	   String dykssj=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dykssj");
	            	   String dyjssj=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dyjssj");
	            	   String dyzjle=JSONObject.parseObject(JSONObject.toJSONString(zydyLstArray.get(g))).getString("dyzjle"); 
	            	   Date kssj = sdf.parse(dykssj);
	            	   Date jssj = sdf.parse(dyjssj);
	            	 //创建Bdczy（转移）
	            	   Bdcdy bdcdy = new Bdcdy();
	            	   bdcdy.setYwh(ywh);
	            	   bdcdy.setDyr(dyr);
	            	   bdcdy.setDyje(dyje);
	            	   bdcdy.setDyrzjh(dyrzjh);            	 
	            	   bdcdy.setDykssj(kssj);
	            	   bdcdy.setDyjssj(jssj);
	            	   bdcdy.setDyzjle(dyzjle);
	            	   bdcdy.setZydyrq(sdf.parse(sjsj));
	            	  cs = bdcsjService.insertBdcdy(bdcdy);  
            	   }
               }
        
		view.addObject("cs", cs);
		return view;
	} 
    /**
     * 调用高拍仪页面
     * @throws UnsupportedEncodingException 
     * **/
    @GetMapping("gpy")
    public String addgpy(HttpServletRequest request,ModelMap modelMap) throws UnsupportedEncodingException{
      	String ywh = request.getParameter("ywh");
    	//String fjfl = request.getParameter("fjfl");
    	//fjfl = java.net.URLDecoder.decode(fjfl,"UTF-8");
    	String ftpUrl = request.getParameter("ftpUrl");
    	String interUsername = request.getParameter("interUsername");
    	String interPassword = request.getParameter("interPassword");
    	String interPort = request.getParameter("interPort");
    	modelMap.put("ftpUrl",ftpUrl);
    	modelMap.put("interUsername",interUsername);
    	modelMap.put("interPassword",interPassword);
    	modelMap.put("interPort",interPort);
    	modelMap.put("slbh",ywh);
    	//modelMap.put("fjfl",fjfl);
        return "admin/bdc/gpy";
    }
    
    /**
     * 在完成件页面编辑附件信息
     * @throws IOException 
     * */
    @GetMapping("editFjxx")
    public String editFjxx(ModelMap modelMap,String slbh) throws IOException{
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
   		FtpHandler f = readConfig.ftpHandler();
		String ftpUrl="ftp://"+f.ftpUrl();
		modelMap.put("ftpUrl",ftpUrl);
		modelMap.put("listHT1", filename1);
		modelMap.put("listHT2", filename2);
		modelMap.put("listHT3", filename3);
		modelMap.put("listHT4", filename4);
		modelMap.put("listHT5", filename5);
		modelMap.put("slbh", slbh);
        return "admin/bdc/editFjxx";
    }
    
    @PostMapping("updateYwzt")
    public ModelAndView addgpy(String slbh,String text,ModelMap modelMap){
    	ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
    	Bdcquery bdcquery = new Bdcquery();
    	bdcquery.setYwh(slbh);
    	bdcquery.setYwzt("2");
    	bdcquery.setDelyy(text);
    	boolean s = bdcsjService.updateYwzt(bdcquery);
    	String msg="";
    	if(s){
    		msg="删除成功";
    	}else{
    		msg="删除失败，请联系管理员查看";
    	}
    	view.addObject("msg", msg);
    	return view;
    }
    
	
}
