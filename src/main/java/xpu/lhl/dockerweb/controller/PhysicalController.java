package xpu.lhl.dockerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/physical")
public class PhysicalController {

    //宿主机基本信息页面
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("physical/physical");
    }

    //宿主机磁盘信息页面
    @RequestMapping("/disk")
    public ModelAndView diskInfo(){
        return new ModelAndView("physical/disk");
    }

    //宿主机CPU信息页面
    @RequestMapping("/cpu")
    public ModelAndView cpuInfo(){
        return new ModelAndView("physical/cpu");
    }

    //宿主机内存信息页面
    @RequestMapping("/memory")
    public ModelAndView memoryInfo(){
        return new ModelAndView("physical/memory");
    }
}