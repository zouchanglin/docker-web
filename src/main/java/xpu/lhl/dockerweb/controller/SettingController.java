package xpu.lhl.dockerweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.config.DockerConfig;
import xpu.lhl.dockerweb.config.RepositoryConfig;
import xpu.lhl.dockerweb.form.DockerIPFrom;
import xpu.lhl.dockerweb.form.RepositoryForm;

import java.util.Map;

@Controller
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private RepositoryConfig repositoryConfig;

    @Autowired
    private DockerConfig dockerConfig;

    @GetMapping("repository-page")
    public ModelAndView getRepositorySettingPage(){
        return new ModelAndView("setting/user-settings");
    }

    @GetMapping("docker-page")
    public ModelAndView getDockerSettingPage(){
        return new ModelAndView("setting/docker-settings");
    }

    @PostMapping("repository")
    public ModelAndView saveRepository(RepositoryForm repositoryForm,
                                       Map<String, String> map){

        repositoryConfig.setServerAddress(repositoryForm.getServerAddress());
        repositoryConfig.setEmail(repositoryForm.getEmail());
        repositoryConfig.setUserName(repositoryForm.getUserName());
        repositoryConfig.setNamespace(repositoryForm.getPassword());
        repositoryConfig.setNamespace(repositoryForm.getNamespace());

        map.put("msg", "修改配置成功");
        map.put("url", "/physical/index");
        return new ModelAndView("common/success");
    }

    @PostMapping("docker")
    public ModelAndView saveDockerIPAndPort(DockerIPFrom dockerIPFrom,
                                            Map<String, String> map){

        dockerConfig.setDockerIp(dockerIPFrom.getDockerIp());
        dockerConfig.setDockerPort(dockerIPFrom.getDockerPort());

        map.put("msg", "修改配置成功");
        map.put("url", "/physical/index");
        return new ModelAndView("common/success");
    }
}
