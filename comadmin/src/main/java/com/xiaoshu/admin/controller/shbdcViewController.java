package com.xiaoshu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.util.DateUtils;

@Controller
@RequestMapping("/admin/shbdc")
public class shbdcViewController {
	
	@GetMapping("shbdcysj")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcysj(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shbdcysj";
    }
	
	@GetMapping("shbdcytj")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcytj(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shbdcytj";
    }
	
	@GetMapping("shbdcywc")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcywc(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shbdcywc";
    }
	
	@GetMapping("sjd")
    @SysLog("新建业务")
    public String sjd(){
        return "admin/shUpdate/sjd";
    }
	
	@GetMapping("/ywall/deleAll")
	public String getDeleteAll() {
		return "admin/shbdc/deleAll";
	}

}
