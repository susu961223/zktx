package com.xiaoshu.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.BdcFileService;
import com.xiaoshu.admin.service.db2.BdcsjService;
import com.xiaoshu.common.config.FtpConfigurer;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.DateUtil;
import com.xiaoshu.common.util.FtpHandler;

@Controller
public class bdcFlieController {

	@Autowired
    UserService userService;
	
	@Autowired
	private FtpConfigurer readConfig;

	@Autowired
	BdcsjService bdcsjService;
	
	@Autowired
	public BdcFileService bdcFileService;

	/**
	 * 上传文件到ftp，文件的基本信息存储在表Bdcfile
	 */
	@PostMapping("bdcUpFile")
	@ResponseBody
	public ModelAndView bdcUpFile(@RequestBody JSONObject  fjljLst,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
		// 解析JSON获取解析json数据
		    JSONObject json = JSON.parseObject(fjljLst.toJSONString());
		    String fjljlst=json.getString("fjljLst");
		    JSONArray fjljlstArray=JSONArray.parseArray(fjljlst);
		    
		    	 String ywh=json.getString("slbh");
		    	 String fjfl=json.getString("fjfl");
		User user = userService.getById(MySysUser.id());
		String ywry = user.getLoginName();
		// 连接FTP
		FtpHandler f = readConfig.ftpHandler();
		// 获取当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String slbh = sdf.format(date);
		String msg = "";
		// 定义Bdcfile
		Bdcfile bdcfile = new Bdcfile();
		// 开始DocBinfile
		String binid = "Bin-" + slbh + "-" + DateUtil.getRandomString(8);
		for(int i=0;i<fjljlstArray.size();i++){
			System.out.println("fjljlstArray.size()=="+fjljlstArray.size());
			 String fjlj=JSONObject.parseObject(JSONObject.toJSONString(fjljlstArray.get(i))).getString("fjlj");
			 System.out.println("fjlj===="+fjlj);
			 // 创建文件夹
			 	String[] k = fjlj.split("/");
//			 boolean m = f.createDir(slbh,ywh,k[2]);
			 	System.out.println("k[1]==="+k[1]);
			 	boolean m = true;
			 	File file= new File(fjlj);
				int one = fjlj.lastIndexOf("/");
				String fileName = fjlj.substring((one + 1), fjlj.length());// 获取文件名
				String fileEndName = fileName.substring(0, fileName.indexOf("."));
				String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);// 文件后缀				
				String path = "/" + slbh + "/" + ywh + "/" + k[2] + "/" + fileEndName + "." + prefix;// 文件路径
				bdcfile.setBinid(binid);
				bdcfile.setYwh(ywh);
				bdcfile.setFilename(fileEndName + "." + prefix);
				bdcfile.setFilesize((int) file.length());
				bdcfile.setExtname(prefix);
				bdcfile.setCreatetime(DateUtil.getDate());
				bdcfile.setCreator(ywry);
				bdcfile.setPath(path);
				List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
				if(filename1 != null && filename1.size()>0){
					msg="上传失败，已经上传过同名称的文件";
				}else{
					if(m){
						InputStream in = new FileInputStream(file);
//						boolean s = f.putFile1(fileName, in, k[2], slbh, ywh);
						boolean s = true;
							if (s) {
								bdcsjService.insertBdcfile(bdcfile);
								msg = "上传成功";
							} else {
								msg = "上传失败";
							}
						}else{
							msg = "创建文件夹失败，请查看链接";
						}
					}
					// 开始上传到FTP
					
			}
		view.addObject("msg", msg);
		return view;
	}
	@PostMapping("bdcUpFile1")
	@ResponseBody
	public ModelAndView bdcUpDateFile(String filepath,String slbh){
		String msg = "";
		ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
		User user = userService.getById(MySysUser.id());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String ywh = sdf.format(date);
		String ywry = user.getLoginName();
		Bdcfile bdcfile = new Bdcfile();
		String[] str = filepath.split("/");
		String binid = "Bin-" + slbh + "-" + DateUtil.getRandomString(8);
		System.out.println("slbh====="+slbh);
		bdcfile.setBinid(binid);
		bdcfile.setYwh(slbh);
		bdcfile.setFilename(str[3]);
		bdcfile.setCreatetime(DateUtil.getDate());
		bdcfile.setCreator(ywry);
		String path = "/" + ywh + "/" + slbh + "/" + str[2] + "/"+str[3];
		bdcfile.setPath(path);
		List<Bdcfile> filename1 = bdcFileService.findAllFileFilenameList(bdcfile);
		if(filename1 != null && filename1.size()>0){
			msg="上传失败，已经上传过同名称的文件";
		}else{
			boolean s = bdcsjService.insertBdcfile(bdcfile);
			if(s){
				msg="上传成功";
			}else{
				msg="上传失败";
			}
		}
		view.addObject("msg", msg);
		return view;
	}
	  @PostMapping("delFj")
	    public ModelAndView delFj(HttpServletRequest request,ModelMap modelMap) throws IOException{
		  ModelAndView view =new ModelAndView(new MappingJackson2JsonView());
		  String ywh = request.getParameter("slbh");
	    	String fjfl = request.getParameter("fjmc");
	    	fjfl = java.net.URLDecoder.decode(fjfl,"UTF-8");
	    	Bdcfile Bdcfile = new Bdcfile();
			Bdcfile.setFilename(fjfl);
			Bdcfile.setYwh(ywh);
			Bdcfile filePath = bdcFileService.findAllFilePathList(Bdcfile);
			System.out.println("查看路徑===="+filePath.getPath());
			String[] s=filePath.getPath().split("/");		
			String str="/"+s[1]+"/"+s[2]+"/"+s[3];
			// 连接FTP
			FtpHandler f = readConfig.ftpHandler();
			boolean sc=f.removeFj(s[4], str);
			
	    	//boolean 
	    	Bdcfile bdcfile=new Bdcfile();
	    	bdcfile.setYwh(ywh);
	    	bdcfile.setFilename(fjfl);
	    	boolean del= bdcFileService.delFj(bdcfile);
	    	view.addObject("del", del);
	    	view.addObject("sc", sc);
			return view;
	    }
	  
	    @GetMapping("addfixx")
	    public String addgpy(HttpServletRequest request,ModelMap modelMap) throws UnsupportedEncodingException{
	      	String ywh = request.getParameter("ywh");
	      	System.out.println("ywh==="+ywh);
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
	    	modelMap.put("slbh",ywh);
	        return "admin/bdc/addFjxx";
	    }
}
