package xpu.lhl.dockerweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.enums.ApplicationNameEnum;
import xpu.lhl.dockerweb.service.AppStoreService;
import xpu.lhl.dockerweb.utils.AppEnumUtil;

import java.util.Map;

@Controller
@RequestMapping("/applications")
public class AppStoreController {
    private final AppStoreService appStoreService;

    @Autowired
    public AppStoreController(AppStoreService appStoreService) {
        this.appStoreService = appStoreService;
    }

    @GetMapping("list")
    public ModelAndView applicationList(){
        return new ModelAndView("app-store/index");
    }

    @GetMapping("download")
    public ModelAndView getApplication(@RequestParam String app, Map<String, Object> map){
        ApplicationNameEnum applicationNameEnum = AppEnumUtil.getByName(app);
        map.put("imageId", applicationNameEnum.getAppImageName());
        map.put("appName", applicationNameEnum.getAppName());
        appStoreService.doNothing();
        return new ModelAndView("app-store/create");
    }
}