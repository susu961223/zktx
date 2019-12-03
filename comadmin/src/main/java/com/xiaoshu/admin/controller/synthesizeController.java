package com.xiaoshu.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcquery;
import com.xiaoshu.admin.model.Bdcsj;
import com.xiaoshu.admin.model.DJshare;
import com.xiaoshu.admin.model.Fcxx;
import com.xiaoshu.admin.model.FjEntity;
import com.xiaoshu.admin.service.db2.BdcsjService;
import com.xiaoshu.admin.service.db2.DJshareService;
import com.xiaoshu.admin.service.db2.FjService;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DatatablesView;
import com.xiaoshu.common.util.ExcelUtils;
import com.xiaoshu.common.util.*;
@Controller
@RequestMapping("admin/system/zhcx")
public class synthesizeController {
	
	@Autowired
	BdcsjService bdcsjService;
	
	@Autowired
	DJshareService dJshareService;
	
	@Autowired
	FjService fjService;
	
    @GetMapping("plxxcx")
    public String bdcjtc(HttpServletRequest request,ModelMap modelMap){
      	String slbh = request.getParameter("path");
    	modelMap.put("slbh",slbh);
        return "admin/zhcx/plxxcx";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("plxxcx")
    public ModelAndView plxxcx(@RequestParam("filein") MultipartFile file) throws Exception{
    	ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
    	List<Bdcquery> list = ExcelUtils.getAll(file);
    	List<Fcxx> list1 = new ArrayList();
    	List<Fcxx> list2 = new ArrayList();
    	String zjhmf="";
    	for(Bdcquery Bdcquery:list){
    		Fcxx flog=new Fcxx();
    		String zjhm = Bdcquery.getZjh();
    		if(zjhm.length()>15){
        		zjhmf=zjhm.substring(0, 6)+zjhm.substring(8,zjhm.length());  			
    		}else{
    			zjhmf=zjhm;
    		}
    		flog.setQlrmc(Bdcquery.getQlr());
    		flog.setZjhm(zjhm);
    		flog.setZjhmf(zjhmf);
    		list2 = bdcsjService.findAllFcxx(flog);
        	System.out.println(list2.size());
    		for(Fcxx fcxx:list2){
    			list1.add(fcxx);
    		}
    	}
    	view.addObject("list2", list1);
    	return view;
    }

    /**  
     * 删除  
     *   
     * @param files  
     */  
    private void deleteFile(File... files) {  
        for (File file : files) {  
            if (file.exists()) {  
                file.delete();  
            }  
        }  
    }
    
  /**
   * 三河原始库房产追溯
   * */
    @GetMapping("dJshare")
    public String dJshare(){      	
        return "admin/zhcx/dJshare";
    }
    /**
     * 三河原始库追溯房产信息
     * */
   	@PostMapping("dJshare")
   	@ResponseBody
   	public String bdcwc(@RequestParam(name = "start", required = false) Integer start,
   			@RequestParam(name = "length", required = false) Integer length,
   			@RequestParam(name = "draw", required = false) Integer draw,
   			@RequestParam(name = "bdcqzh", required = false) String bdcqzh) throws ParseException{
   		DatatablesView<DJshare> dataTable = new DatatablesView<DJshare>();
   		
   		DJshare test = new DJshare();
   		test.setStart(start + 1);
   		test.setLength(start + 10);
   		test.setBdcqzh(bdcqzh);
 
   		List<DJshare> data = dJshareService.selectDJshare(test);
   		System.out.println("data==="+data.size());
   		int total = dJshareService.findCountDJshare(test);
   		dataTable.setDraw(draw);
   		dataTable.setData(data);
   		dataTable.setRecordsTotal(total);
   		dataTable.setLength(length);
   		String data2 = JSON.toJSONString(dataTable);
   		return data2;	
       }
    /**
     * 三河原始库追溯通过权证号跳转
     * */
   	@GetMapping(value="qsxxtz")
	public String qsxxtz (String bdcqzh,ModelMap modelMap) throws UnsupportedEncodingException{
   		//bdcqzh = java.net.URLDecoder.decode(bdcqzh,"UTF-8"); 
		Bdcsj bdcsj = new Bdcsj();
		bdcsj.setBdcqzh(bdcqzh);
		
		modelMap.put("bdczh", bdcqzh);
		
		DJshare test = new DJshare();
		test.setBdcqzh(bdcqzh);
		List<DJshare> qsxx=dJshareService.selectQsxxList(test);
		List<DJshare> qsxxYwr=dJshareService.selectQsxxYwrList(test);
		String ywr="";
		String ywrzjh="";
		for(DJshare i:qsxxYwr) {
			ywr=i.getYwr();
			ywrzjh=i.getYwrzjh();
		}
		modelMap.put("qsxxList", qsxx);
		if(ywr==null||ywr.equals("")) {
			modelMap.put("ywr", "无");
		}else{
			modelMap.put("ywr", ywr);
		}
		if(ywrzjh==null||ywrzjh.equals("")){
			modelMap.put("ywrzjh", "无");
		}else {
			modelMap.put("ywrzjh", ywrzjh);
		}
		//modelMap.put("qsxxYwrList", qsxxYwr);
    //查询是否有抵押状态
    	int dyzt = bdcsjService.getByDyzt(bdcqzh);
    	if(dyzt>0) { 
    		modelMap.put("dyzt", "是");
    	}else {
    		modelMap.put("dyzt", "否");
    	}
    //查询是否有查封状态
    	int cfzt=bdcsjService.getByCfzt(bdcqzh);
    	if(cfzt>0) {
    		modelMap.put("cfzt", "是");    		
    	}else {
    		modelMap.put("cfzt", "否");   
    	}
    //查询是否有异议登记
    	String yyzt=bdcsjService.getByYyzt(bdcqzh);
    	if(yyzt!=null) {
    		modelMap.put("yyzt", "是");
    	}else {
    		modelMap.put("yyzt", "否");
    	}
    //查询是否有预告登记	
    	String ygzt=bdcsjService.getByYgzt(bdcqzh);
    	if(ygzt!=null) {
    		modelMap.put("ygzt", "是");
    	}else {
    		modelMap.put("ygzt", "否");
    	}
    	
		
		System.out.println(bdcqzh+"权属信息"+qsxx.size());
 
		List<Bdcsj> dylist = bdcsjService.selectDy(bdcsj);
		List<Bdcsj> dylist1 = new ArrayList();
		if(dylist!=null){
			for(Bdcsj i:dylist){
				if(i.getZwlxqssj()==null){
					i.setZwlxqssj(" ");
				}
				if(i.getZwlxjssj()==null){
					i.setZwlxjssj(" ");
				}
				dylist1.add(i);
			}
		}

		modelMap.put("dylist", dylist1);
		System.out.println(bdcqzh+"抵押信息"+dylist1.size());
		
		List<Bdcsj> cflist = bdcsjService.selectCf(bdcsj);
		List<Bdcsj> cflist1 = new ArrayList();
		if(cflist!=null){
			for(Bdcsj i:cflist){
				if(i.getCfjssj()==null){
					i.setCfjssj(" ");
				}
				if(i.getCfqssj()==null){
					i.setCfqssj(" ");
				}
				cflist1.add(i);
			}
		}

		modelMap.put("cflist", cflist1);
		System.out.println(bdcqzh+"查封信息"+cflist1.size());
		
		
		return "admin/zhcx/qsxxtz";
	}
   
   	/**
   	 * 三河原始库追溯导出
   	 * */
   	@RequestMapping(value="/exportQsFile",method=RequestMethod.POST)
	public void exportDyFile(String bdcqzh,HttpServletResponse res) {
   		try {
   		//查询的记录
   			DJshare test = new DJshare();
   			test.setBdcqzh(bdcqzh);
   			List<DJshare> qsxx=dJshareService.selectDJshareAll(test);
			HSSFWorkbook wb=ExcelUtils.daochu(qsxx);
			OutputStream output=res.getOutputStream();
		    res.reset();
		    res.setContentType("application/vnd.ms-excel");
		    res.setHeader("Content-disposition",
					"attachment; filename=" + new String("追溯信息表".getBytes("GB2312"), "8859_1") +".xls");
		    res.flushBuffer();
		    wb.write(output);
		    output.flush();
		    output.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
   	
   	/**
   	 * 三河原始库附件查看
   	 * */
   	@GetMapping("fJck")
    public String fJck(){      	
        return "admin/zhcx/fJck";
    }
   	
   	@PostMapping("fJck")
    public ModelAndView fJckTest(String ywh){
   		ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
   		FjEntity fjEntity = new FjEntity();
   		fjEntity.setYwh(ywh);
   		List<FjEntity> fjlist= fjService.findFj(fjEntity);
   		List<FjEntity> fjPath = new ArrayList<>();
   		if(fjlist!=null){
   			for(FjEntity fj:fjlist){
   				FjEntity fjEntitys = new FjEntity();
   				fjEntitys.setSjmc("path/"+fj.getSjmc()+".jpg");
   				fjPath.add(fjEntitys);
   			}
   		}
   		view.addObject("fjlist", fjlist);
   		view.addObject("fjPath", fjPath);
        return view;
    }
   	
	@GetMapping("yulan1")
	@ResponseBody
	public String createFolw(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws IOException {	
		File srcFile = new File("/path/"+filePath+".JPG");
		InputStream fis = new FileInputStream(srcFile);
		OutputStream os = null;
		try {
			response.setContentType("image/jpeg"); 
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024*20];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fis.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
