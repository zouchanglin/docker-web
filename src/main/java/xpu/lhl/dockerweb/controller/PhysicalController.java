package xpu.lhl.dockerweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.config.ProjectConfig;
import xpu.lhl.dockerweb.service.MonitorService;
import xpu.lhl.dockerweb.utils.DiskFreeUtils;
import xpu.lhl.dockerweb.utils.PhysicalFacilityUtil;
import xpu.lhl.dockerweb.vo.HostBasicInfoVO;

import java.util.Map;


@Controller
@RequestMapping("/physical")
public class PhysicalController {
    private final ProjectConfig projectConfig;
    private final MonitorService monitorService;

    @Autowired
    public PhysicalController(ProjectConfig projectConfig,
                              MonitorService monitorService) {
        this.projectConfig = projectConfig;
        this.monitorService = monitorService;
    }

    //宿主机基本信息页面
    @RequestMapping("index")
    public ModelAndView index(Map<String, Object> map){
        String osName = PhysicalFacilityUtil.getOsName();
        Integer cpuNumber = PhysicalFacilityUtil.getCPUNumber();
        map.put("osName", osName);
        map.put("cpuNumber", cpuNumber);
        HostBasicInfoVO basicInfoVO = PhysicalFacilityUtil.getHostAndJVMInfo();
        map.put("basicInfoVO", basicInfoVO);
        return new ModelAndView("physical/physical", map);
    }

    //宿主机磁盘信息页面
    @RequestMapping("disk")
    public ModelAndView diskInfo(Map<String, Object> map){
        monitorService.startSendDiskMessage();
        Map<String, String> diskInfo = DiskFreeUtils.getInfo();
        map.put("diskInfo", diskInfo);
        map.put("projectUrl", projectConfig.getUrl());
        return new ModelAndView("physical/disk", map);
    }

    //宿主机CPU信息页面
    @RequestMapping("cpu")
    public ModelAndView cpuInfo(Map<String, String> map){
        monitorService.startSendCPUMessage();
        map.put("projectUrl", projectConfig.getUrl());
        return new ModelAndView("physical/cpu");
    }

    //宿主机内存信息页面
    @RequestMapping("memory")
    public ModelAndView memoryInfo(Map<String, String> map){
        monitorService.startSendMemoryMessage();
        map.put("projectUrl", projectConfig.getUrl());
        return new ModelAndView("physical/memory", map);
    }
}