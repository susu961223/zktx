package com.xiaoshu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.common.annotation.SysLog;

@Controller
@RequestMapping("admin/system/fg")
public class fgController {
	
    @GetMapping("fgsj")
    @SysLog("跳转系统房管页面")
    public String bdcsj(){
        return "admin/fg/fgsj";
    }
}
