package xpu.lhl.dockerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("user/index");
    }
}
