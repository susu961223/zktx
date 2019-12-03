package com.xiaoshu.admin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshu.admin.entity.User;
import com.xiaoshu.admin.model.Bdcfile;
import com.xiaoshu.admin.service.UserService;
import com.xiaoshu.admin.service.db2.BdcFileService;
import com.xiaoshu.common.config.FtpConfigurer;
import com.xiaoshu.common.config.MySysUser;
import com.xiaoshu.common.util.FtpHandler;

@Controller
public class PreviewController {
	
	@Autowired
	private FtpConfigurer readConfig;
	
	@Autowired
	public BdcFileService bdcFileService;
	
    @Autowired
    UserService userService;
	
/*	//预览ftp文件
	@SuppressWarnings("static-access")
	@GetMapping("yulan")
    @ResponseBody
    public String yulan(HttpSession session,HttpServletResponse response,
    		HttpServletRequest request,String filename,String ywh)throws Exception{
		Bdcfile Bdcfile = new Bdcfile();
		filename = java.net.URLDecoder.decode(filename,"UTF-8");
		Bdcfile.setFilename(filename);
		Bdcfile.setYwh(ywh);
		System.out.println("filename===="+filename+"====ywh============"+ywh);
		Bdcfile filePath = bdcFileService.findAllFilePathList(Bdcfile);
		System.out.println("查看路徑===="+filePath.getPath());
		String path = "E:/bf/"+filename;
		File tmp = File.createTempFile("lzq", ".jpg", new File("E:\\bf\\"));
		FtpHandler ftp = readConfig.ftpHandler();
		File files = ftp.asFile(filePath.getPath(),tmp);
		File file = openOfficeHolder.openOfficeToPDF(path,files);
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "inline; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) != -1)
            out.write(buf, 0, len);
        br.close();
        out.close();
        return "admin/sw/swsj";
	}*/
	
	@SuppressWarnings("static-access")
	@GetMapping("yulan1")
	@ResponseBody
	public String createFolw(HttpServletRequest request,
			HttpServletResponse response, String filename,String ywh) throws IOException {
		// response.setContentType("image/*");
		Bdcfile Bdcfile = new Bdcfile();
		filename = java.net.URLDecoder.decode(filename,"UTF-8");
		Bdcfile.setFilename(filename);
		Bdcfile.setYwh(ywh);
		Bdcfile filePath = bdcFileService.findAllFilePathList(Bdcfile);
		FtpHandler ftp = readConfig.ftpHandler();
		System.out.println("预览的路径===="+filePath.getPath());
		InputStream fis = null;
		OutputStream os = null;
		try {
			fis = ftp.getInputStream1(filePath.getPath());
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			System.out.println("读流"+buffer);
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
		return "ok";
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("imgPath")
	@ResponseBody
	public String createFolw1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// response.setContentType("image/*");
		User user = userService.getById(MySysUser.id());
		String path = userService.imgPath(user);
		System.out.println(path);
		FtpHandler ftp = readConfig.ftpHandler();
		InputStream fis = null;
		OutputStream os = null;
		try {
			fis = ftp.getInputStream(path);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
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
		return "ok";
	}
	
}
