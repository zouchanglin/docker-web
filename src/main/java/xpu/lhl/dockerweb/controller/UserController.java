package xpu.lhl.dockerweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.form.UserLoginForm;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("user/index");
    }

    @PostMapping("login")
    public ModelAndView login(UserLoginForm userLoginForm,
                              Map<String, Object> map){
        log.info("UserLoginForm={}", userLoginForm);
        return new ModelAndView("redirect:/physical/index");
    }

    @GetMapping("logout")
    public ModelAndView logout(Map<String, Object> map){
        //log.info("UserLoginForm={}", userLoginForm);
        return new ModelAndView("redirect:/");
    }
}