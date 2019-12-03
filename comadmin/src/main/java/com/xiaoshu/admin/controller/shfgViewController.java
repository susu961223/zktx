package com.xiaoshu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.util.DateUtils;

@Controller
@RequestMapping("/admin/shfg")	
public class shfgViewController {
	
	@GetMapping("shfgysj")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcysj(){
        return "admin/shbdc/shfgysj";
    }
	
	@GetMapping("shfgytj")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcytj(){
        return "admin/shbdc/shfgytj";
    }
	
	@GetMapping("shfgywc")
    @SysLog("跳转系统三河不动产已收件")
    public String shbdcywc(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shfgywc";
    }

}
