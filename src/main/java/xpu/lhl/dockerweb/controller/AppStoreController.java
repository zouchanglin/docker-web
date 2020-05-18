package xpu.lhl.dockerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/applications")
public class AppStoreController {

    @GetMapping("list")
    public ModelAndView applicationList(){

        return new ModelAndView("app-store/index");
    }
}
