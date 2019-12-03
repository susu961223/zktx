package com.xiaoshu.admin.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.common.annotation.SysLog;
import com.xiaoshu.common.util.DateUtils;

@Controller
@RequestMapping("/admin/shsw")
public class shswViewController {
	
	@RequiresPermissions("sys:shsw:shswysj")
	@GetMapping("shswysj")
    @SysLog("跳转系统三河税务已收件")
    public String shbdcysj(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shswysj";
    }
	
	@RequiresPermissions("sys:shsw:shswytj")
	@GetMapping("shswywj")
    @SysLog("跳转系统三河税务已提交")
    public String shbdcytj(){
        return "admin/shbdc/shswytj";
    }
	
	@RequiresPermissions("sys:shsw:shswywc")
	@GetMapping("shswywc")
    @SysLog("跳转系统三河税务已完成")
    public String shbdcywc(ModelMap modelMap){
		String ywh = DateUtils.getNewYear()+DateUtils.getNewMouth()+DateUtils.getNewDay();
		modelMap.put("ywh", ywh);
        return "admin/shbdc/shswywc";
    }


}
