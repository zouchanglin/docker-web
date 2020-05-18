package xpu.lhl.dockerweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xpu.lhl.dockerweb.config.DockerConfig;
import xpu.lhl.dockerweb.config.ProjectConfig;
import xpu.lhl.dockerweb.config.RepositoryConfig;
import xpu.lhl.dockerweb.form.DockerIPFrom;
import xpu.lhl.dockerweb.form.RepositoryForm;
import xpu.lhl.dockerweb.service.DockerVersionService;
import xpu.lhl.dockerweb.vo.DockerVersionVO;

import java.util.Map;

@Controller
@RequestMapping("/setting")
public class SettingController {

    private final RepositoryConfig repositoryConfig;
    private final DockerConfig dockerConfig;
    private final ProjectConfig projectConfig;
    private final DockerVersionService dockerVersionService;

    @Autowired
    public SettingController(RepositoryConfig repositoryConfig,
                             DockerConfig dockerConfig,
                             DockerVersionService dockerVersionService,
                             ProjectConfig projectConfig) {
        this.repositoryConfig = repositoryConfig;
        this.dockerConfig = dockerConfig;
        this.dockerVersionService = dockerVersionService;
        this.projectConfig = projectConfig;
    }

    @GetMapping("docker-version")
    public ModelAndView getDockerVersion(Map<String, Object> map){
        DockerVersionVO dockerVersion = dockerVersionService.getDockerVersion();
        map.put("dockerVersion", dockerVersion);
        return new ModelAndView("setting/docker-version", map);
    }

    @GetMapping("repository-page")
    public ModelAndView getRepositorySettingPage(Map<String, Object> map){
        map.put("repositoryConfig", repositoryConfig);
        return new ModelAndView("setting/user-settings", map);
    }

    @GetMapping("docker-page")
    public ModelAndView getDockerSettingPage(Map<String, Object> map){
        map.put("dockerConfig", dockerConfig);
        return new ModelAndView("setting/docker-settings", map);
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

        // 修改管理系统的URL，方便WebSocket连接
        projectConfig.setUrl(dockerIPFrom.getDockerIp() + ":8080");

        map.put("msg", "修改配置成功");
        map.put("url", "/physical/index");
        return new ModelAndView("common/success");
    }
}
